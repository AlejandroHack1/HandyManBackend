package com.handyman.backend.infraestructure.models;

import com.handyman.backend.services.application.domain.Service;
import com.handyman.backend.services.application.domain.valueObjs.*;

public class ServiceDTO {
    private Long id;
    private String idTecnico;
    private String idServicio;
    private String fInicio;
    private String fFin;

    private String status;

    public ServiceDTO(Long id, String idTecnico, String idServicio, String fInicio, String fFin) {
        this.id = id;
        this.idTecnico = idTecnico;
        this.idServicio = idServicio;
        this.fInicio = fInicio;
        this.fFin = fFin;
    }

    public ServiceDTO() {
    }

    public Service toDomain() {
        return new Service(
                new ServiceId(id),
                new IdentificacionTecnico(idTecnico),
                new IdentificacionServicio(idServicio),
                new FechaInicio(fInicio),
                new FechaFin(fFin)

        );
    }

    public static ServiceDTO fromDomain(Service service) {
        ServiceDTO servicetDTO = new ServiceDTO();
        servicetDTO.setId(service.getId().getValue());
        servicetDTO.setIdTecnico(service.getIdTecnico().getValue());
        servicetDTO.setIdServicio(service.getIdServicio().getValue());
        servicetDTO.setfInicio(service.getfInicio().getValue());
        servicetDTO.setfFin(service.getfFin().getValue());

        return servicetDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public String getfInicio() {
        return fInicio;
    }

    public void setfInicio(String fInicio) {
        this.fInicio = fInicio;
    }

    public String getfFin() {
        return fFin;
    }

    public void setfFin(String fFin) {
        this.fFin = fFin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServiceDTO{" +
                "id=" + id +
                ", idTecnico='" + idTecnico + '\'' +
                ", idServicio='" + idServicio + '\'' +
                ", fInicio=" + fInicio +
                ", fFin=" + fFin +
                '}';
    }
}
