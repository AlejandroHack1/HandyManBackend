package com.handyman.backend.services.application.ports.input;

import com.handyman.backend.commons.MultipleUseCase;
import com.handyman.backend.infraestructure.models.CalculoDTO;

import java.util.Optional;

public interface QueryCalculoByIdAndWeekUseCase extends MultipleUseCase<String, String, Optional<CalculoDTO>> {
}
