package com.handyman.backend.services.application.services;

import com.handyman.backend.infraestructure.models.ServiceDTO;
import com.handyman.backend.services.application.domain.Service;
import com.handyman.backend.services.application.ports.input.UpdateServiceUseCase;
import com.handyman.backend.services.application.ports.output.ServiceRepository;

import java.util.Optional;

@org.springframework.stereotype.Service
public class UpdateServiceService implements UpdateServiceUseCase {

    private final ServiceRepository serviceRepository;

    public UpdateServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceDTO execute(ServiceDTO serviceDTO) {

        Service service = serviceDTO.toDomain();

        Optional<Service> serviceBD = serviceRepository.get(service.getId());

        if(serviceBD.isPresent()) {
            serviceRepository.update(service);
            serviceDTO.setStatus("Updated");
        } else {
            serviceDTO.setStatus("No Updated");
        }
        return serviceDTO;
    }
}
