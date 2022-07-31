package com.handyman.backend.services.application.ports.input;

import com.handyman.backend.commons.UseCase;
import com.handyman.backend.infraestructure.models.CalculoDTO;

import java.util.Optional;

public interface QueryCalculoByIdUseCase extends UseCase<String, Optional<CalculoDTO>> {
}
