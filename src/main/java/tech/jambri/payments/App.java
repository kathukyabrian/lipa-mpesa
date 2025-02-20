package tech.jambri.payments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.jambri.payments.config.ApplicationProperties;
import tech.jambri.payments.config.ConfigUtil;
import tech.jambri.payments.core.Mpesa;
import tech.jambri.payments.core.ServiceRepository;
import tech.jambri.payments.core.factory.ServiceRepositoryFactory;
import tech.jambri.payments.dto.MpesaSTKResponse;

import java.util.Properties;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.debug("system|starting lipa-mpesa service|about to load properties");

        try {
            Runtime.getRuntime().addShutdownHook(new ShutdownHook());
            logger.info("system|registered shutdown hook.");
            Properties properties = ConfigUtil.readConfig();
            ApplicationProperties applicationProperties = ConfigUtil.getProperties(properties);
            logger.debug("system|application properties loaded|properties={}", properties);
            ConfigUtil.validateProperties(applicationProperties);
            logger.debug("system|validated properties|everything looks good|about to register props to service repository");
            ServiceRepository serviceRepository = ServiceRepositoryFactory.getServiceRepository();
            serviceRepository.init(applicationProperties);
            logger.debug("system|initialized service repository|about to start service repository");
            serviceRepository.start();
        } catch (Exception e) {
            logger.error("system|encountered exception", e);
        }
    }

    static class ShutdownHook extends Thread {
        @Override
        public void run() {
            logger.info("system|about to shutdown");
            try {
                ServiceRepository serviceRepository = ServiceRepositoryFactory.getServiceRepository();
                serviceRepository.stop();
                logger.info("system|stopped the service");
            } catch (Exception ex) {
                logger.error("system|error stopping ", ex);
            }
        }
    }
}
