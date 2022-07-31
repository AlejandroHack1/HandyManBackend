package com.handyman.backend.services.application.services;

import com.handyman.backend.infraestructure.models.ServiceDTO;
import com.handyman.backend.services.application.domain.Service;
import com.handyman.backend.services.application.domain.valueObjs.ServiceId;
import com.handyman.backend.services.application.ports.input.QueryServiceByIdUseCase;
import com.handyman.backend.services.application.ports.output.ServiceRepository;

import java.util.Optional;

@org.springframework.stereotype.Service
public class QueryServiceByIdService implements QueryServiceByIdUseCase {

    private final ServiceRepository serviceRepository;

    public QueryServiceByIdService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Optional<ServiceDTO> execute(Long id) {
        ServiceId serviceId = new ServiceId(id);

        Optional<Service> serviceOptional = serviceRepository.get(serviceId);

        return serviceOptional.map(service -> {
            ServiceDTO serviceDTO = ServiceDTO.fromDomain(service);
            return serviceDTO;
        });
    }
}
