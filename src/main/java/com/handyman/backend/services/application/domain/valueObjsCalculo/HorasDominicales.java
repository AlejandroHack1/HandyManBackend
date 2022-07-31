package com.handyman.backend.services.application.domain.valueObjsCalculo;

import org.apache.commons.lang3.Validate;

public class HorasDominicales {

    private final String value;

    public HorasDominicales(String value) {
        Validate.notNull(value, "Horas Dominicales no puede ser nulo");
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
