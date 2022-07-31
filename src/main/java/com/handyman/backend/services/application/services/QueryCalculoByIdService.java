package com.handyman.backend.services.application.services;

import com.handyman.backend.infraestructure.models.CalculoDTO;
import com.handyman.backend.services.application.domain.Calculo;
import com.handyman.backend.services.application.domain.valueObjsCalculo.TecnicoId;
import com.handyman.backend.services.application.ports.input.QueryCalculoByIdUseCase;
import com.handyman.backend.services.application.ports.output.CalculoHorasRepository;

import java.util.Optional;

@org.springframework.stereotype.Service
public class QueryCalculoByIdService implements QueryCalculoByIdUseCase {

    private final CalculoHorasRepository calculoHorasRepository;

    public QueryCalculoByIdService(CalculoHorasRepository calculoHorasRepository) {
        this.calculoHorasRepository = calculoHorasRepository;
    }

    @Override
    public Optional<CalculoDTO> execute(String id) {
        TecnicoId tecnicoId = new TecnicoId(id);

        Optional<Calculo> calculoOptional = calculoHorasRepository.get(tecnicoId);

        return calculoOptional.map(calculo -> {
            CalculoDTO calculoDTO = CalculoDTO.fromDomain(calculo);
            return calculoDTO;
        });
    }
}
