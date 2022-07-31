package com.handyman.backend.services.application.ports.input;

import com.handyman.backend.commons.UseCase;
import com.handyman.backend.infraestructure.models.ServiceDTO;

import java.util.Optional;

public interface QueryServiceByIdUseCase extends UseCase<Long, Optional<ServiceDTO>> {


}
