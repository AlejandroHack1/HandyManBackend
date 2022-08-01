package com.handyman.backend.infraestructure.adapters.output;

import com.handyman.backend.infraestructure.logSystem.Log;
import com.handyman.backend.infraestructure.models.CalculoDAO;
import com.handyman.backend.infraestructure.models.ServiceDAO;
import com.handyman.backend.operation.CalculateHours;
import com.handyman.backend.services.application.domain.Calculo;
import com.handyman.backend.services.application.domain.Service;
import com.handyman.backend.services.application.domain.valueObjs.ServiceId;
import com.handyman.backend.services.application.domain.valueObjsCalculo.Semanas;
import com.handyman.backend.services.application.domain.valueObjsCalculo.TecnicoId;
import com.handyman.backend.services.application.ports.output.CalculoHorasRepository;
import com.handyman.backend.services.application.ports.output.ServiceRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


@Repository
public class MysqlServiceRepository implements ServiceRepository, CalculoHorasRepository {

    private final DataSource dataSource;

    public MysqlServiceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(Service service) {

        CalculateHours calculateHours = new CalculateHours();
        String[] result = calculateHours.operation(service);

        String sql = "INSERT INTO servicio (idTecnico, idServicio, fechaInicio, fechaFin) values (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, service.getIdTecnico().getValue());
            preparedStatement.setString(2, service.getIdServicio().getValue());
            preparedStatement.setString(3, service.getfInicio().getValue());
            preparedStatement.setString(4, service.getfFin().getValue());

            preparedStatement.execute();

            storeCalculo(result, service.getIdTecnico().getValue());


        } catch (SQLException exception) {
            System.out.println("Errror DB....: " + exception.getMessage());
            throw new RuntimeException("Error queryng database", exception);
        }

    }

    @Override
    public Optional<Service> get(ServiceId serviceId) {

        String sql = "Select * From servicio Where idTecnico = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, serviceId.getValue());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ServiceDAO serviceDAO = ServiceDAO.fromResultSet(resultSet);
                // logic ---
                Service service = serviceDAO.toDomain();

                CalculateHours calculateHours = new CalculateHours();

                String input = "2022-07-23 11:45";
                String format = "yyyyMMdd";

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = null;
                try {
                    date = df.parse(service.getfInicio().getValue());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int week = cal.get(Calendar.WEEK_OF_YEAR);
                System.out.println(week);
                String[] result = calculateHours.operation(service);
                System.out.println("Calculo Semanas: " + result[0]);

                return Optional.of(service);
            } else {
                return Optional.empty();
            }

        } catch (SQLException exception) {

            throw new RuntimeException("Error queryn database", exception);
        }

    }

    @Override
    public void update(Service service) {
        String sql = "Update servicio Set idTecnico = ?, idServicio = ?, fechaInicio = ? , fechaFin = ? Where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, service.getIdTecnico().getValue());
            preparedStatement.setString(2, service.getIdServicio().getValue());
            preparedStatement.setString(3, service.getfInicio().getValue());
            preparedStatement.setString(4, service.getfFin().getValue());

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            Log log = new Log(exception.getMessage(), "Error queryn database");
            log.logger();
            throw new RuntimeException("Error queryn database", exception);
        }
    }

    @Override
    public Boolean delete(ServiceId serviceId) {
        String sql = "Delete from services Where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, serviceId.getValue());

            Boolean result = preparedStatement.execute();

            return result;

        } catch (SQLException exception) {
            Log log = new Log(exception.getMessage(), "Error queryn database");
            throw new RuntimeException("Error queryng database", exception);
        }
    }


    //base de datos calculo con el resultado de operación de las horas trabajadas


    public void storeCalculo(String[] dataHours, String value) {

        String sql = "INSERT INTO calculo (semanas, idTecnico, horasNormales, horasNocturnas, horasDominicales, horasNormalesExtras, horasNocturnasExtras, horasDominicalesExtras) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, dataHours[0].replaceAll("(^\\[|\\]$)", "").replaceAll("[,]\\s+", ","));
            preparedStatement.setString(2, value);
            preparedStatement.setString(3, dataHours[1].replaceAll("(^\\[|\\]$)", "").replaceAll("[,]\\s+", ","));
            preparedStatement.setString(4, dataHours[2].replaceAll("(^\\[|\\]$)", "").replaceAll("[,]\\s+", ","));
            preparedStatement.setString(5, dataHours[3].replaceAll("(^\\[|\\]$)", "").replaceAll("[,]\\s+", ","));
            preparedStatement.setString(6, dataHours[4].replaceAll("(^\\[|\\]$)", "").replaceAll("[,]\\s+", ","));
            preparedStatement.setString(7, dataHours[5].replaceAll("(^\\[|\\]$)", "").replaceAll("[,]\\s+", ","));
            preparedStatement.setString(8, dataHours[6].replaceAll("(^\\[|\\]$)", "").replaceAll("[,]\\s+", ","));

            preparedStatement.execute();

        } catch (SQLException exception) {
            System.out.println("Errror DB....: " + exception.getMessage());
            throw new RuntimeException("Error queryng database", exception);
        }
    }

    @Override
    public void store(Calculo calculo) {

    }

    @Override
    public Optional<Calculo> get(TecnicoId tecnicoId) {

        String sql = "Select * From calculo Where idTecnico = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, tecnicoId.getValue());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                CalculoDAO calculoDAO = CalculoDAO.fromResultSet(resultSet);
                // logic ---
                Calculo calculo = calculoDAO.toDomain();
                return Optional.of(calculo);
            } else {
                return Optional.empty();
            }

        } catch (SQLException exception) {

            throw new RuntimeException("Error queryn database", exception);
        }

    }

    public String getElementPosition(String semana, String tecnicoId) {

        String position = null;
        //encontrar la posición del número de la semana a consultar en list semanas de la BD.
        String sqlFindIndex = "SELECT FIND_IN_SET(?, semanas) as position from calculo where idTecnico = ?";

        try (Connection connection = dataSource.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindIndex)) {

            preparedStatement.setString(1, semana);
            preparedStatement.setString(2, tecnicoId);

            ResultSet result = preparedStatement.executeQuery();

            result.next();
            try {

                position = result.getString("position");

                /*CalculoDTO calculo = new CalculoDTO();
                calculo.setPosition(position);

                System.out.println(position);*/

                return position;
            } catch (SQLException exception) {

                System.out.println(exception);
            }

        } catch (SQLException exception) {
            throw new RuntimeException("Error queryn database", exception);
        }

        return position;

    }


    @Override
    public Optional<Calculo> getByWeek(TecnicoId tecnicoId, Semanas semanas) {

        System.out.println(tecnicoId.getValue());
        System.out.println(semanas.getValue());

        String position = getElementPosition(semanas.getValue(), tecnicoId.getValue());

        if (Integer.parseInt(position) != 0) {
            //obtiene elemento de la lista correspondiente a la posición encontrada en semanas.
            String sqlGetElementsFromList = "select SUBSTRING_INDEX( SUBSTRING_INDEX( id, ',', 1 ), ',', -1 ) as id, \n" +
                    "SUBSTRING_INDEX( SUBSTRING_INDEX( semanas, ',', ? ), ',', -1 ) as semanas,\n" +
                    "SUBSTRING_INDEX( SUBSTRING_INDEX( idTecnico, ',', 1 ), ',', -1 ) as idTecnico,\n" +
                    "SUBSTRING_INDEX( SUBSTRING_INDEX( horasNormales, ',', ? ), ',', -1 ) as horasNormales,\n" +
                    "SUBSTRING_INDEX( SUBSTRING_INDEX( horasNocturnas, ',', ? ), ',', -1 ) as horasNocturnas,\n" +
                    "SUBSTRING_INDEX( SUBSTRING_INDEX( horasDominicales, ',', ? ), ',', -1 ) as horasDominicales,\n" +
                    "SUBSTRING_INDEX( SUBSTRING_INDEX( horasNormalesExtras, ',', ? ), ',', -1 ) as horasNormalesExtras,\n" +
                    "SUBSTRING_INDEX( SUBSTRING_INDEX( horasNocturnasExtras, ',', ? ), ',', -1 ) as horasNocturnasExtras,\n" +
                    "SUBSTRING_INDEX( SUBSTRING_INDEX( horasDominicalesExtras, ',', ? ), ',', -1 ) as horasDominicalesExtras\n" +
                    "\n" +
                    "from calculo where idTecnico = ?;";

            try (Connection connection = dataSource.getConnection();

                 PreparedStatement preparedStatement = connection.prepareStatement(sqlGetElementsFromList)) {

                preparedStatement.setString(1, position);
                preparedStatement.setString(2, position);
                preparedStatement.setString(3, position);
                preparedStatement.setString(4, position);
                preparedStatement.setString(5, position);
                preparedStatement.setString(6, position);
                preparedStatement.setString(7, position);

                preparedStatement.setString(8, tecnicoId.getValue());

                ResultSet result = preparedStatement.executeQuery();


                result.next();
                //if (result.next() == true) {
                try {

                    CalculoDAO calculoDAO = CalculoDAO.fromResultSet(result);

                    // logic ---
                    Calculo calculo = calculoDAO.toDomain();
                    return Optional.of(calculo);

                } catch (SQLException exception) {

                    System.out.println(exception);
                    return Optional.empty();
                }

            } catch (SQLException exception) {

                throw new RuntimeException("Error queryn database", exception);
            }
        } else {
            return Optional.empty();
        }
    }
}
