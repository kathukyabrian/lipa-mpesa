package tech.jambri.payments.core.factory;


import tech.jambri.payments.config.ApplicationProperties;
import tech.jambri.payments.core.ServiceRepository;

/**
 * singleton factory for service repository
 */
public class ServiceRepositoryFactory {
    private static ServiceRepository serviceRepository;

    public static ServiceRepository getServiceRepository() {
        if (serviceRepository == null) {
            serviceRepository = new ServiceRepository();
            try {
                serviceRepository.init();
                serviceRepository.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return serviceRepository;
    }

    public static ApplicationProperties getApplicationProperties() {
        ServiceRepository serviceRepository = getServiceRepository();
        return serviceRepository.getApplicationProperties();
    }
}
