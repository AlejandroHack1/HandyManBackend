package com.handyman.backend.services.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class FechaInicio {
    private final String value;

    public FechaInicio(String value) {
        System.out.println(value);
        Validate.notNull(value, "Fecha Inicio no puede ser nulo");
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
