package com.handyman.backend.services.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class ServiceId {

    private final Long value;

    public ServiceId(Long value) {
        Validate.notNull(value, "Servicio Id no puede ser nulo");
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
