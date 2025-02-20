package tech.jambri.payments.core.factory;


import tech.jambri.payments.core.ServiceRepository;

/**
 * singleton factory for service repository
 */
public class ServiceRepositoryFactory {
    private static ServiceRepository serviceRepository;

    public static ServiceRepository getServiceRepository() {
        if (serviceRepository == null) {
            serviceRepository = new ServiceRepository();
        }
        return serviceRepository;
    }
}
