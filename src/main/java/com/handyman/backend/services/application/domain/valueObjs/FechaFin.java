package com.handyman.backend.services.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class FechaFin {

    private final String value;

    public FechaFin(String value) {
        Validate.notNull(value, "Fecha Fin no puede ser nulo");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
