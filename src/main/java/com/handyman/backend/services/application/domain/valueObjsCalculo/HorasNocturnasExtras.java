package com.handyman.backend.services.application.domain.valueObjsCalculo;

import org.apache.commons.lang3.Validate;

public class HorasNocturnasExtras {

    private final String value;

    public HorasNocturnasExtras(String value) {
        Validate.notNull(value, "Horas Nocturnas Extras no puede ser nulo");
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
