package com.handyman.backend.infraestructure.models;

import com.handyman.backend.services.application.domain.Calculo;
import com.handyman.backend.services.application.domain.valueObjsCalculo.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculoDAO {

    private Long id;
    private String idTecnico;
    private String semanas;
    private String hNormales;
    private String hNocturnas;
    private String hDominicales;
    private String hNormalesExtras;
    private String hNocturnasExtras;
    private String hDominicalesExtras;

    public CalculoDAO(Long id, String idTecnico, String semanas, String hNormales, String hNocturnas, String hDominicales, String hNormalesExtras, String hNocturnasExtras, String hDominicalesExtras) {
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

    public CalculoDAO() {
    }

    public Calculo toDomain() {
        return new Calculo(
                new CalculoId(id),
                new TecnicoId(idTecnico),
                new Semanas(semanas),
                new HorasNormales(hNormales),
                new HorasNocturnas(hNocturnas),
                new HorasDominicales(hDominicales),
                new HorasNormalesExtras(hNormalesExtras),
                new HorasNocturnasExtras(hNocturnasExtras),
                new HorasDominicalesExtras(hDominicalesExtras)

        );
    }

    public static CalculoDAO fromDomain(Calculo calculo) {
        CalculoDAO calculoDAO = new CalculoDAO(
                calculo.getId().getValue(),
                calculo.getIdTecnico().getValue(),
                calculo.getSemanas().getValue(),
                calculo.gethNormales().getValue(),
                calculo.gethNocturnas().getValue(),
                calculo.gethDominicales().getValue(),
                calculo.gethNormalesExtras().getValue(),
                calculo.gethNocturnasExtras().getValue(),
                calculo.gethDominicalesExtras().getValue()

        );
        return calculoDAO;
    }

    public static CalculoDAO fromResultSet(ResultSet resultSet) throws SQLException {
        CalculoDAO calculoDAO = new CalculoDAO();
        calculoDAO.setId(resultSet.getLong("id"));
        calculoDAO.setSemanas(resultSet.getString("semanas"));
        calculoDAO.setIdTecnico(resultSet.getString("idTecnico"));
        calculoDAO.sethNormales(resultSet.getString("horasNormales"));
        calculoDAO.sethNocturnas(resultSet.getString("horasNocturnas"));
        calculoDAO.sethDominicales(resultSet.getString("horasDominicales"));
        calculoDAO.sethNormalesExtras(resultSet.getString("horasNormalesExtras"));
        calculoDAO.sethNocturnasExtras(resultSet.getString("horasNocturnasExtras"));
        calculoDAO.sethDominicalesExtras(resultSet.getString("horasDominicalesExtras"));
        return calculoDAO;
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

    public String getSemanas() {
        return semanas;
    }

    public void setSemanas(String semanas) {
        this.semanas = semanas;
    }

    public String gethNormales() {
        return hNormales;
    }

    public void sethNormales(String hNormales) {
        this.hNormales = hNormales;
    }

    public String gethNocturnas() {
        return hNocturnas;
    }

    public void sethNocturnas(String hNocturnas) {
        this.hNocturnas = hNocturnas;
    }

    public String gethDominicales() {
        return hDominicales;
    }

    public void sethDominicales(String hDominicales) {
        this.hDominicales = hDominicales;
    }

    public String gethNormalesExtras() {
        return hNormalesExtras;
    }

    public void sethNormalesExtras(String hNormalesExtras) {
        this.hNormalesExtras = hNormalesExtras;
    }

    public String gethNocturnasExtras() {
        return hNocturnasExtras;
    }

    public void sethNocturnasExtras(String hNocturnasExtras) {
        this.hNocturnasExtras = hNocturnasExtras;
    }

    public String gethDominicalesExtras() {
        return hDominicalesExtras;
    }

    public void sethDominicalesExtras(String hDominicalesExtras) {
        this.hDominicalesExtras = hDominicalesExtras;
    }
}
