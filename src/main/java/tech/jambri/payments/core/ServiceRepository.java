package tech.jambri.payments.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.jambri.payments.config.ApplicationProperties;

public class ServiceRepository implements Runnable {

    private static final Logger log = LogManager.getLogger(ServiceRepository.class);
    private ApplicationProperties applicationProperties;
    private Thread thread;

    public void init(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.thread = new Thread(this, "mpesa");
    }

    public void start() {
        this.thread.start();
    }

    public void stop() {
        this.thread.interrupt();
    }

    public ApplicationProperties getApplicationProperties() {
        return applicationProperties;
    }


    @Override
    public void run() {
        log.info("started {} thread. waiting for requests...", Thread.currentThread().getName());
        while (true) {

        }
    }
}
