package com.handyman.backend.services.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class IdentificacionTecnico {

    private final String value;

    public IdentificacionTecnico(String value) {
        Validate.notNull(value, "Identificación Técnico no puede ser nulo");
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
