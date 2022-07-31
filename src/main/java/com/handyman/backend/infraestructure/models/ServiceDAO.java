package com.handyman.backend.infraestructure.models;

import com.handyman.backend.services.application.domain.Service;
import com.handyman.backend.services.application.domain.valueObjs.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDAO {

    private Long id;
    private String idTecnico;
    private String idServicio;
    private String fInicio;
    private String fFin;



    public ServiceDAO(Long id, String idTecnico, String idServicio, String fInicio, String fFin) {
        this.id = id;
        this.idTecnico = idTecnico;
        this.idServicio = idServicio;
        this.fInicio = fInicio;
        this.fFin = fFin;
    }

    public ServiceDAO() {
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

    public static ServiceDAO fromDomain(Service service) {
        ServiceDAO serviceDAO = new ServiceDAO(
                service.getId().getValue(),
                service.getIdTecnico().getValue(),
                service.getIdServicio().getValue(),
                service.getfInicio().getValue(),
                service.getfFin().getValue()

                );
        return serviceDAO;
    }

    public static ServiceDAO fromResultSet(ResultSet resultSet) throws SQLException {
        ServiceDAO aerviceDAO = new ServiceDAO();
        aerviceDAO.setId(resultSet.getLong("id"));
        aerviceDAO.setIdServicio(resultSet.getString("idServicio"));
        aerviceDAO.setIdTecnico(resultSet.getString("idTecnico"));
        aerviceDAO.setfInicio(resultSet.getString("fechaInicio"));
        aerviceDAO.setfFin(resultSet.getString("fechaFin"));
        return aerviceDAO;
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
}
