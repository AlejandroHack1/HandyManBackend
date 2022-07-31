package com.handyman.backend.services.application.domain;

import com.handyman.backend.services.application.domain.valueObjs.*;

public class Service {

    private final ServiceId id;
    private final IdentificacionTecnico idTecnico;
    private final IdentificacionServicio idServicio;
    private final FechaInicio fInicio;
    private final FechaFin fFin;

    public Service(ServiceId id, IdentificacionTecnico idTecnico, IdentificacionServicio idServicio, FechaInicio fInicio, FechaFin fFin) {
        this.id = id;
        this.idTecnico = idTecnico;
        this.idServicio = idServicio;
        this.fInicio = fInicio;
        this.fFin = fFin;
    }

    public ServiceId getId() {
        return id;
    }

    public IdentificacionTecnico getIdTecnico() {
        return idTecnico;
    }

    public IdentificacionServicio getIdServicio() {
        return idServicio;
    }

    public FechaInicio getfInicio() {
        return fInicio;
    }

    public FechaFin getfFin() {
        return fFin;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", idTecnico=" + idTecnico +
                ", idServicio=" + idServicio +
                ", fInicio=" + fInicio +
                ", fFin=" + fFin +
                '}';
    }
}
