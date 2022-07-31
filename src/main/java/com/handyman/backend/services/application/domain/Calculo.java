package com.handyman.backend.services.application.domain;

import com.handyman.backend.services.application.domain.valueObjsCalculo.*;

public class Calculo {

    private final CalculoId id;
    private final TecnicoId idTecnico;
    private final Semanas semanas;
    private final HorasNormales hNormales;
    private final HorasNocturnas hNocturnas;
    private final HorasDominicales hDominicales;
    private final HorasNormalesExtras hNormalesExtras;
    private final HorasNocturnasExtras hNocturnasExtras;
    private final HorasDominicalesExtras hDominicalesExtras;

    public Calculo(CalculoId id, TecnicoId idTecnico, Semanas semanas, HorasNormales hNormales, HorasNocturnas hNocturnas, HorasDominicales hDominicales, HorasNormalesExtras hNormalesExtras, HorasNocturnasExtras hNocturnasExtras, HorasDominicalesExtras hDominicalesExtras) {
        this.id = id;
        this.idTecnico = idTecnico;
        this.semanas = semanas;
        this.hNormales = hNormales;
        this.hNocturnas = hNocturnas;
        this.hDominicales = hDominicales;
        this.hNormalesExtras = hNormalesExtras;
        this.hNocturnasExtras = hNocturnasExtras;
        this.hDominicalesExtras = hDominicalesExtras;
    }


    public CalculoId getId() {
        return id;
    }

    public TecnicoId getIdTecnico() {
        return idTecnico;
    }

    public Semanas getSemanas() {
        return semanas;
    }

    public HorasNormales gethNormales() {
        return hNormales;
    }

    public HorasNocturnas gethNocturnas() {
        return hNocturnas;
    }

    public HorasDominicales gethDominicales() {
        return hDominicales;
    }

    public HorasNormalesExtras gethNormalesExtras() {
        return hNormalesExtras;
    }

    public HorasNocturnasExtras gethNocturnasExtras() {
        return hNocturnasExtras;
    }

    public HorasDominicalesExtras gethDominicalesExtras() {
        return hDominicalesExtras;
    }

    @Override
    public String toString() {
        return "Calculo{" +
                "id=" + id +
                ", idTecnico=" + idTecnico +
                ", Semanas=" + semanas +
                ", hNormales=" + hNormales +
                ", hNocturnas=" + hNocturnas +
                ", hDominicales=" + hDominicales +
                ", hNormalesExtras=" + hNormalesExtras +
                ", hNocturnasExtras=" + hNocturnasExtras +
                ", hDominicalesExtras=" + hDominicalesExtras +
                '}';
    }
}
