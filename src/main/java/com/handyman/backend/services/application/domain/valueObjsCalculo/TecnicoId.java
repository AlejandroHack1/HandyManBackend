package com.handyman.backend.services.application.domain.valueObjsCalculo;

import org.apache.commons.lang3.Validate;

public class TecnicoId {

    private final String value;

    public TecnicoId(String value) {
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
