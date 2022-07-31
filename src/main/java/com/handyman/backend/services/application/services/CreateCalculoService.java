package com.handyman.backend.services.application.services;

import com.handyman.backend.infraestructure.models.CalculoDTO;
import com.handyman.backend.services.application.domain.Calculo;
import com.handyman.backend.services.application.domain.valueObjsCalculo.*;
import com.handyman.backend.services.application.ports.input.CreateCalculoUseCase;
import com.handyman.backend.services.application.ports.output.CalculoHorasRepository;

@org.springframework.stereotype.Service
public class CreateCalculoService implements CreateCalculoUseCase {

    private final CalculoHorasRepository calculoHorasRepository;

    public CreateCalculoService(CalculoHorasRepository calculoHorasRepository) {
        this.calculoHorasRepository = calculoHorasRepository;
    }

    @Override
    public CalculoDTO execute(CalculoDTO calculoDTO) {
        Calculo calculo = new Calculo(null,
                new TecnicoId(calculoDTO.getIdTecnico()),
                new Semanas(calculoDTO.getSemanas()),
                new HorasNormales(calculoDTO.gethNormales()),
                new HorasNocturnas(calculoDTO.gethNocturnas()),
                new HorasDominicales(calculoDTO.gethDominicales()),
                new HorasNormalesExtras(calculoDTO.gethNormalesExtras()),
                new HorasNocturnasExtras(calculoDTO.gethNocturnasExtras()),
                new HorasDominicalesExtras(calculoDTO.gethDominicalesExtras()));


        ///logic store
        calculoHorasRepository.store(calculo);

        return calculoDTO;
    }
}
