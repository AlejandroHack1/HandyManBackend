package com.handyman.backend.services.application.domain.valueObjsCalculo;

import org.apache.commons.lang3.Validate;

public class Semanas {

    private final String value;

    public Semanas(String value) {
        Validate.notNull(value, "Semanas no puede ser nulo");
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
