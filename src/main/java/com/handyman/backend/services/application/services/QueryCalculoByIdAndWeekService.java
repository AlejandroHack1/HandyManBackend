package com.handyman.backend.services.application.services;

import com.handyman.backend.infraestructure.models.CalculoDTO;
import com.handyman.backend.services.application.domain.Calculo;
import com.handyman.backend.services.application.domain.valueObjsCalculo.Semanas;
import com.handyman.backend.services.application.domain.valueObjsCalculo.TecnicoId;
import com.handyman.backend.services.application.ports.input.QueryCalculoByIdAndWeekUseCase;
import com.handyman.backend.services.application.ports.output.CalculoHorasRepository;

import java.util.Optional;

@org.springframework.stereotype.Service
public class QueryCalculoByIdAndWeekService implements QueryCalculoByIdAndWeekUseCase {

    private final CalculoHorasRepository calculoHorasRepository;

    public QueryCalculoByIdAndWeekService(CalculoHorasRepository calculoHorasRepository) {
        this.calculoHorasRepository = calculoHorasRepository;
    }

    @Override
    public Optional<CalculoDTO> execute(String s, String s2) {
        TecnicoId tecnicoId = new TecnicoId(s);
        Semanas semanas = new Semanas(s2);

        Optional<Calculo> calculoOptional = calculoHorasRepository.getByWeek(tecnicoId , semanas);

        return calculoOptional.map(calculo -> {
            CalculoDTO calculoDTO = CalculoDTO.fromDomain(calculo);
            return calculoDTO;
        });
    }
}
