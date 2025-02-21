package tech.jambri.payments.core.factory;

import org.junit.jupiter.api.Test;
import tech.jambri.payments.config.ApplicationProperties;
import tech.jambri.payments.core.ServiceRepository;

class ServiceRepositoryFactoryTest {
    @Test
    void testSingletonCreated() {
        ServiceRepository serviceRepository = ServiceRepositoryFactory.getServiceRepository();
        ServiceRepository anotherServiceRepository = ServiceRepositoryFactory.getServiceRepository();

        assert serviceRepository.hashCode() == anotherServiceRepository.hashCode();
    }

}