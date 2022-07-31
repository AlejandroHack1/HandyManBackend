package com.handyman.backend.services.application.domain.valueObjsCalculo;

import org.apache.commons.lang3.Validate;

public class CalculoId {

    private final Long value;

    public CalculoId(Long value) {
        Validate.notNull(value, "Calculo Id no puede ser nulo");
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
