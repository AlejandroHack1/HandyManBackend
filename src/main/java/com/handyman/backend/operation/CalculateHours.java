package com.handyman.backend.operation;

import com.handyman.backend.services.application.domain.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalculateHours {
    public String[] operation(Service service) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date fechaInicio;
        Date fechaFinal;
        String[] resultadosOperaciones = new String[7];

        try {
            fechaInicio = df.parse(String.valueOf(service.getfInicio()));
            fechaFinal = df.parse(String.valueOf(service.getfFin()));

            Calendar calInicio = Calendar.getInstance();
            Calendar calFin = Calendar.getInstance();

            calInicio.setTime(fechaInicio);
            calFin.setTime(fechaFinal);

            int week1 = calInicio.get(Calendar.WEEK_OF_YEAR);
            int week2 = calFin.get(Calendar.WEEK_OF_YEAR);

            List<Integer> findWeeks = getNumbersInRange(week1, week2);

            //hallar diferencia en dias
            int numeroDias = calcularDias(service);

            System.out.println("dias: " + numeroDias);

            long difference_In_Time
                    = fechaFinal.getTime() - fechaInicio.getTime();

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            System.out.println("diferencia en horas totales: " + difference_In_Hours * numeroDias);

            List<Integer> HorasTrabajo = HorasTrabajo(calInicio, calFin, findWeeks, numeroDias);
            resultadosOperaciones[0] = String.valueOf(findWeeks);

            System.out.println("Horas trabajo Diurnas, Nocturnas: " + HorasTrabajo);

            resultadosOperaciones[1] = String.valueOf(HorasTrabajo.get(0));
            resultadosOperaciones[2] = String.valueOf(HorasTrabajo.get(1));
            resultadosOperaciones[3] = String.valueOf(HorasTrabajo.get(2));
            resultadosOperaciones[4] = String.valueOf(HorasTrabajo.get(3));
            resultadosOperaciones[5] = String.valueOf(HorasTrabajo.get(4));
            resultadosOperaciones[6] = String.valueOf(HorasTrabajo.get(5));


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return resultadosOperaciones;
    }

    private List<Integer> HorasTrabajo(Calendar calInicio, Calendar calFin, List<Integer> findWeeks, int numeroDias) {

        List<Integer> HorasPorSemana = new ArrayList<>();
        int calcDiurna = 0;
        int calcNocturna = 0;
        int calcDominicales = 0;
        int acumulado = 0;
        int faltante = 0;
        int horasDia = 0;
        int totalDiurnas = 0;
        int TotalNocturnas = 0;
        int nocturnaExtra = 0;
        int diurnaExtra = 0;
        int dominicalExtra = 0;
        int acumuladoLimite = 0;
        int acumuladoDiurnas = 0;
        int acumuladoNocturnas = 0;
        for (int i = 1; i <= numeroDias; i++) {

            calcDiurna = calcularHorasDiurna(calInicio, calFin);
            calcNocturna = calcularHorasNocturna(calInicio, calFin);
            calcDominicales = calcularHorasDominicales(calInicio, calFin);

            /*acumuladoDiurnas += calcDiurna;
            acumuladoNocturnas += calcNocturna;*/

            //horasDia = calcDiurna + calcNocturna + calcDominicales;

            if (Calendar.SUNDAY == calInicio.get(Calendar.DAY_OF_WEEK) ||
                    Calendar.SUNDAY == calFin.get(Calendar.DAY_OF_WEEK)) {

                if (i == 1) {
                    horasDia = calcDiurna + calcNocturna + calcDominicales;

                } else {
                    horasDia = calcDiurna + calcNocturna;
                }
            } else {

                horasDia = calcDiurna + calcNocturna;

            }

            System.out.println("diurna:" + calcDiurna);

            acumulado += horasDia;

            if (acumulado <= 48) {
                acumuladoLimite += horasDia;
            }

            System.out.println("acumulado: " + acumulado);
            System.out.println("acumuladoLimite: " + acumuladoLimite);

            if (acumulado <= 48) {

                totalDiurnas += calcDiurna;
                TotalNocturnas += calcNocturna;

            } else {

                acumuladoDiurnas += calcDiurna;
                System.out.println("acumulado Diurnas:" + acumuladoDiurnas);

                acumuladoNocturnas += calcNocturna;
                System.out.println("acumulado Nocturnas:" + acumuladoNocturnas);

                faltante = (48 - acumuladoLimite);
                if(faltante != 0){
                    if (calcNocturna != 0) {
                        nocturnaExtra = calcNocturna - faltante;
                        diurnaExtra = calcDiurna;
                        TotalNocturnas += faltante;

                    } else {
                        diurnaExtra = calcDiurna - faltante;
                        nocturnaExtra = calcNocturna;
                        totalDiurnas += faltante;
                    }
                } else{
                    if (calcNocturna != 0) {
                        nocturnaExtra = acumuladoNocturnas - TotalNocturnas;
                        diurnaExtra = acumuladoDiurnas - totalDiurnas;

                    } else {
                        diurnaExtra = acumuladoDiurnas - totalDiurnas;
                        nocturnaExtra = acumuladoNocturnas - TotalNocturnas;
                    }

                }

            }

        }

        if (acumulado >= 48) {
            dominicalExtra = calcDominicales;
            calcDominicales = 0;
        }

        if (Calendar.SUNDAY == calInicio.get(Calendar.DAY_OF_WEEK) && Calendar.SUNDAY != calFin.get(Calendar.DAY_OF_WEEK)
                && totalDiurnas > 0) {
            totalDiurnas -= calcDominicales;
        }

        if (Calendar.SUNDAY == calInicio.get(Calendar.DAY_OF_WEEK) && Calendar.SUNDAY != calFin.get(Calendar.DAY_OF_WEEK)
                && TotalNocturnas > 0) {
            TotalNocturnas -= calcDominicales;
        }

        HorasPorSemana.add(totalDiurnas);
        HorasPorSemana.add(TotalNocturnas);
        HorasPorSemana.add(calcDominicales);
        HorasPorSemana.add(diurnaExtra);
        HorasPorSemana.add(nocturnaExtra);
        HorasPorSemana.add(dominicalExtra);


        return HorasPorSemana;

    }

    /*encontrar las semanas entre la semana de la fecha Inicial y la semana de la Fecha final
    Ejemplo si en fecha Inicial corresponde a la semana 6 y fecha final a la semana 9.
    econtrar las semanas intermedias es decir la semana 7 y 8. */
    public List<Integer> getNumbersInRange(int start, int end) {
        List<Integer> result = new ArrayList<>();

        //si las semanas de fecha Inicio y fecha Fin no son iguales
        if (start != end) {
            for (int i = start; i <= end; i++) {
                result.add(i);
            }
        } else {
            result.add(start);
        }

        return result;

    }

    private int calcularDias(Service service) {

        int numberOfDays = 1;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date date1;
        Date date2;
        try {
            date1 = df.parse(String.valueOf(service.getfInicio()));
            date2 = df.parse(String.valueOf(service.getfFin()));

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date1);
            cal2.setTime(date2);

            while (cal1.before(cal2)) {
                numberOfDays++;
                cal1.add(Calendar.DATE, 1);
            }


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return numberOfDays;
    }

    public int calcularHorasDiurna(Calendar calInicio, Calendar calFin) {

        int CalculoHorasTrabajadasDiurna = 0;


        // Diferencia entre horas fecha inicial fecha final.
        if (calInicio.get(Calendar.HOUR_OF_DAY) >= 7 && calFin.get(Calendar.HOUR_OF_DAY) <= 20) {

            for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < calFin.get(Calendar.HOUR_OF_DAY); i++) {
                CalculoHorasTrabajadasDiurna++;
            }
        }

        //fuera del rango agrega por defecto 13 horas normales diurnos
        else if (calInicio.get(Calendar.HOUR_OF_DAY) >= 1 && calFin.get(Calendar.HOUR_OF_DAY) <= 24) {
            CalculoHorasTrabajadasDiurna = 13;
        }


        return CalculoHorasTrabajadasDiurna;
    }

    public int calcularHorasNocturna(Calendar calInicio, Calendar calFin) {

        int CalculoHorasTrabajadasNocturnas = 0;

        if (calInicio.get(Calendar.HOUR_OF_DAY) >= 1 && calInicio.get(Calendar.HOUR_OF_DAY) <= 7) {

            for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < 7; i++) {
                CalculoHorasTrabajadasNocturnas++;
            }
        }

        if (calFin.get(Calendar.HOUR_OF_DAY) >= 20 && calFin.get(Calendar.HOUR_OF_DAY) <= 24) {
            for (int i = calFin.get(Calendar.HOUR_OF_DAY); i > 20; i--) {
                CalculoHorasTrabajadasNocturnas++;
            }
        }


        return CalculoHorasTrabajadasNocturnas;
    }

    public int calcularHorasDominicales(Calendar calInicio, Calendar calFin) {

        int CalculoHorasTrabajadasDominicales = 0;

        if (Calendar.SUNDAY == calInicio.get(Calendar.DAY_OF_WEEK) && Calendar.SUNDAY == calFin.get(Calendar.DAY_OF_WEEK)) {
            for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < calFin.get(Calendar.HOUR_OF_DAY); i++) {
                CalculoHorasTrabajadasDominicales++;
            }
        } else if (Calendar.SUNDAY == calInicio.get(Calendar.DAY_OF_WEEK)) {

            for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < calFin.get(Calendar.HOUR_OF_DAY); i++) {
                CalculoHorasTrabajadasDominicales++;
            }
        } else if (Calendar.SUNDAY == calFin.get(Calendar.DAY_OF_WEEK)) {
            CalculoHorasTrabajadasDominicales = 0;

            for (int i = calFin.get(Calendar.HOUR_OF_DAY); i >= calInicio.get(Calendar.HOUR_OF_DAY); i--) {
                CalculoHorasTrabajadasDominicales++;
            }
        }

        return CalculoHorasTrabajadasDominicales;

    }
}




