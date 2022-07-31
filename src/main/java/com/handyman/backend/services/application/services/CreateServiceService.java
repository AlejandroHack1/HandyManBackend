package com.handyman.backend.services.application.services;

import com.handyman.backend.infraestructure.models.ServiceDTO;
import com.handyman.backend.services.application.domain.Service;
import com.handyman.backend.services.application.domain.valueObjs.FechaFin;
import com.handyman.backend.services.application.domain.valueObjs.FechaInicio;
import com.handyman.backend.services.application.domain.valueObjs.IdentificacionServicio;
import com.handyman.backend.services.application.domain.valueObjs.IdentificacionTecnico;
import com.handyman.backend.services.application.ports.input.CreateServiceUseCase;
import com.handyman.backend.services.application.ports.output.ServiceRepository;

@org.springframework.stereotype.Service
public class CreateServiceService implements CreateServiceUseCase {

    private final ServiceRepository serviceRepository;

    public CreateServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceDTO execute(ServiceDTO serviceDTO) {
        Service service = new Service(null,
                new IdentificacionTecnico(serviceDTO.getIdTecnico()),
                new IdentificacionServicio(serviceDTO.getIdServicio()),
                new FechaInicio(serviceDTO.getfInicio()),
                new FechaFin(serviceDTO.getfFin()));


        ///logic store
        serviceRepository.store(service);

        serviceDTO.setStatus("Created");
        return serviceDTO;
    }
}
