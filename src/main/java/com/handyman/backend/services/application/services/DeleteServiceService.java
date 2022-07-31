package com.handyman.backend.services.application.services;

import com.handyman.backend.services.application.domain.Service;
import com.handyman.backend.services.application.domain.valueObjs.ServiceId;
import com.handyman.backend.services.application.ports.input.DeleteServiceUseCase;
import com.handyman.backend.services.application.ports.output.ServiceRepository;

import java.util.Optional;

@org.springframework.stereotype.Service
public class DeleteServiceService implements DeleteServiceUseCase {
    private final ServiceRepository serviceRepository;

    public DeleteServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Boolean execute(Long id) {
        ServiceId serviceId = new ServiceId(id);

        Optional<Service> service = serviceRepository.get(serviceId);

        if (service.isPresent()) {
            serviceRepository.delete(serviceId);
        }

        return service.isPresent();
    }
}
