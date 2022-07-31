package com.handyman.backend.services.application.ports.output;

import com.handyman.backend.services.application.domain.Calculo;
import com.handyman.backend.services.application.domain.valueObjsCalculo.Semanas;
import com.handyman.backend.services.application.domain.valueObjsCalculo.TecnicoId;

import java.util.Optional;

public interface CalculoHorasRepository {

    void store(Calculo calculo);

    Optional<Calculo> get(TecnicoId tecnicoId);

    Optional<Calculo> getByWeek(TecnicoId tecnicoId, Semanas semanas);
}
