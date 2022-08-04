package com.handyman.backend.operation;

import com.handyman.backend.services.application.domain.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalculateHours2 {


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

    /*Horas Dominicales Extra: Horas trabajadas el día Domingo después de que
    el técnico ya ha trabajado 48 horas esa semana.*/

    public List<Integer> HorasExtrasDominicales(List<Integer> horasNormales, List<Integer> horasNocturnas, List<Integer> horasDominicales) {

        List<Integer> HorasDominicalesExtras = new ArrayList<>();

        for (int i = 0; i < horasNormales.size(); i++) {

            if (horasNormales.get(i).intValue() + horasNocturnas.get(i).intValue() > 48 && horasDominicales.get(i) != 0) {
                HorasDominicalesExtras.add(horasDominicales.get(i));
            } else {
                HorasDominicalesExtras.add(0);
            }
        }

        System.out.println("horas dominicales extras: " + HorasDominicalesExtras);
        return HorasDominicalesExtras;
    }

    /*Horas nocturnas Extra: Horas trabajadas de lunes a sábado entre las 8:00
    PM y las 7:00 AM después de que el técnico ya ha trabajado 48 horas esa
    semana.*/
    public List<Integer> HorasExtrasNocturnas(List<Integer> horasNormales, List<Integer> horasNocturnas) {

        List<Integer> HorasNocturnasExtras = new ArrayList<>();

        for (int i = 0; i < horasNocturnas.size(); i++) {

            //si la hora nocturna es mayor a 48 en la semana, se resta y se obtiene las extras.
            if (horasNocturnas.get(i).intValue() + horasNormales.get(i).intValue() > 48) {
                HorasNocturnasExtras.add(horasNocturnas.get(i).intValue() + horasNormales.get(i).intValue() - 48);
            } else {
                HorasNocturnasExtras.add(0);
            }
        }

        System.out.println("horas nocturnas extras: " + HorasNocturnasExtras);
        return HorasNocturnasExtras;
    }

    /*Horas Normales Extra: Horas trabajadas de lunes a sábado entre las 7:00
    AM y las 8:00 PM después de que el técnico ya ha trabajado 48 horas esa
    semana.*/
    public List<Integer> HorasExtrasNormales(List<Integer> horasNormales) {

        List<Integer> HorasNormalesExtras = new ArrayList<>();

        for (int i = 0; i < horasNormales.size(); i++) {

            //si la hora normal es mayor a 48 en la semana, se agregan las demás horas como extras.
            if (horasNormales.get(i).intValue() > 48) {
                HorasNormalesExtras.add(horasNormales.get(i) - 48);
            } else {
                HorasNormalesExtras.add(0);
            }
        }

        System.out.println("horas normales extras: " + HorasNormalesExtras);
        return HorasNormalesExtras;

    }


    //Horas trabajadas el día Domingo.
    public List<Integer> HorasDominicales(Calendar calInicio, Calendar calFin, List<Integer> findWeeks) {

        int numberOfSundayHours = 0;

        List<Integer> HorasDominicales = new ArrayList<>();

        //una semana
        if (findWeeks.size() == 1) {

            if (Calendar.SUNDAY == calInicio.get(Calendar.DAY_OF_WEEK)) {

                for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < 24; i++) {
                    numberOfSundayHours++;
                }

                HorasDominicales.add(numberOfSundayHours);

            } else {

                HorasDominicales.add(0);
            }


        } else if (findWeeks.size() >= 2) {
            //El dia Domingo se cuenta las horas desde que inició hasta las 24 Horas.
            if (Calendar.SUNDAY == calInicio.get(Calendar.DAY_OF_WEEK)) {

                for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < 24; i++) {
                    numberOfSundayHours++;
                }

                HorasDominicales.add(numberOfSundayHours);

            } else {
                HorasDominicales.add(0);
            }


            if (findWeeks.size() > 2) {

                for (int i = 1; i <= findWeeks.size() - 2; i++) {
                    HorasDominicales.add(0);
                }
            }

            if (Calendar.SUNDAY == calFin.get(Calendar.DAY_OF_WEEK)) {
                numberOfSundayHours = 0;

                for (int i = calFin.get(Calendar.HOUR_OF_DAY); i >= 1; i--) {
                    numberOfSundayHours++;
                }

                HorasDominicales.add(numberOfSundayHours);

            } else {
                HorasDominicales.add(0);
            }

        }

        System.out.println("horas dominicales:" + HorasDominicales);

        return HorasDominicales;

    }

    //Horas nocturnas: Horas trabajadas de lunes a sábado entre las 8:00 PM y las 7:00 AM
    public List<Integer> HorasNocturnas(Calendar calInicio, Calendar calFin, List<Integer> findWeeks, int numberOfDays) {

        int numberOfNightHours = 0;
        List<Integer> HorasNocturnas = new ArrayList<>();


        //para una semana
        if (findWeeks.size() == 1 && numberOfDays == 1) {
            numberOfNightHours = 0;
            if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK) && calInicio.get(Calendar.HOUR_OF_DAY) >= 20 && calFin.get(Calendar.HOUR_OF_DAY) <= 24) {

                for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < calFin.get(Calendar.HOUR_OF_DAY); i++) {
                    numberOfNightHours++;
                }

            } else if (Calendar.SUNDAY != calFin.get(Calendar.DAY_OF_WEEK) && calFin.get(Calendar.HOUR_OF_DAY) >= 1 && calFin.get(Calendar.HOUR_OF_DAY) <= 7) {

                for (int i = calFin.get(Calendar.HOUR_OF_DAY); i >= 1; i--) {
                    numberOfNightHours++;
                }

            } else if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK) && calInicio.get(Calendar.HOUR_OF_DAY) >= 1 && calFin.get(Calendar.HOUR_OF_DAY) <= 24) {
                for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < 7; i++) {
                    numberOfNightHours++;
                }
                for (int i = calFin.get(Calendar.HOUR_OF_DAY); i > 20; i--) {
                    numberOfNightHours++;
                }

            }

            HorasNocturnas.add(numberOfNightHours);

        }

        if (findWeeks.size() == 1 && numberOfDays >= 2 && numberOfDays <= 6) {

            numberOfNightHours = 0;
            int contador = 0;

            if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK) && calInicio.get(Calendar.HOUR_OF_DAY) >= 1 && calInicio.get(Calendar.HOUR_OF_DAY) <= 7) {

                for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < 7; i++) {
                    numberOfNightHours++;
                }
                numberOfNightHours += 4;


            } else if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK) && calInicio.get(Calendar.HOUR_OF_DAY) >= 8) {

                numberOfNightHours += 4;


            } else if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK) && calInicio.get(Calendar.HOUR_OF_DAY) >= 20 && calInicio.get(Calendar.HOUR_OF_DAY) <= 24) {
                for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < 24; i++) {
                    numberOfNightHours++;
                }
            }

            if (Calendar.SUNDAY != calFin.get(Calendar.DAY_OF_WEEK)) {
                if (calFin.get(Calendar.HOUR_OF_DAY) >= 20 && calFin.get(Calendar.HOUR_OF_DAY) <= 24) {
                    for (int i = calFin.get(Calendar.HOUR_OF_DAY); i > 20; i--) {
                        numberOfNightHours++;
                    }

                    numberOfNightHours += 7;

                } else if (calFin.get(Calendar.HOUR_OF_DAY) >= 7 && calFin.get(Calendar.HOUR_OF_DAY) <= 20) {
                    numberOfNightHours += 7;
                } else if (calFin.get(Calendar.HOUR_OF_DAY) >= 1 && calFin.get(Calendar.HOUR_OF_DAY) <= 7) {
                    for (int i = calFin.get(Calendar.HOUR_OF_DAY); i >= 1; i--) {
                        numberOfNightHours++;
                    }
                }
            }

            if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK)) {
                for (int i = 1; i <= numberOfDays - 2; i++) {
                    contador += 11;
                }
            } else {
                for (int i = 1; i <= numberOfDays - 1; i++) {
                    contador += 11;
                }
            }


            HorasNocturnas.add(numberOfNightHours + contador);

        }

        //más de una semana
        if (findWeeks.size() >= 2) {

            int numberOfNightHoursFirstWeek = 0;
            int contador = 0;

            //cuenta las horas nocturnas desde las 8 o la hora mayor a las 8 (20 hora militar) hasta las 12(24) y luego sumar las 7 horas restantes hasta las 7 a.m
            //para la fecha de Inicio
            if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK) && calInicio.get(Calendar.HOUR_OF_DAY) >= 20 && calInicio.get(Calendar.HOUR_OF_DAY) <= 24) {

                for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < 24; i++) {
                    numberOfNightHoursFirstWeek++;
                }

                numberOfNightHoursFirstWeek += 7;
            }

            //si Inicia desde la 1 de la mañana hasta las 7 A.M
            else if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK) && calInicio.get(Calendar.HOUR_OF_DAY) >= 1 && calInicio.get(Calendar.HOUR_OF_DAY) <= 7) {

                for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < 7; i++) {
                    numberOfNightHoursFirstWeek++;
                }

            } else if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK) && calInicio.get(Calendar.HOUR_OF_DAY) >= 8 && calInicio.get(Calendar.HOUR_OF_DAY) <= 20) {
                numberOfNightHoursFirstWeek += 7;
            }

            if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK)) {
                for (int i = 1; i <= numberOfDays - 2; i++) {
                    contador += 11;
                }
            } else {
                for (int i = 1; i <= numberOfDays - 1; i++) {
                    contador += 11;
                }
            }

            //semana de la fecha inicial
            HorasNocturnas.add(numberOfNightHoursFirstWeek + contador);


            //si ingres más de dos semanas, para las semanas completas se multiplica las 11 horas de un dia por los 6 dias de la semana
            for (int i = 1; i <= findWeeks.size() - 2; i++) {
                HorasNocturnas.add(11 * 6);
            }

            int numberOfNightHoursLastWeek = 0;

            if (Calendar.SUNDAY != calFin.get(Calendar.DAY_OF_WEEK)) {

                if (calFin.get(Calendar.HOUR_OF_DAY) >= 20 && calFin.get(Calendar.HOUR_OF_DAY) <= 24) {

                    for (int i = calFin.get(Calendar.HOUR_OF_DAY); i >= 20; i--) {
                        numberOfNightHoursLastWeek++;
                    }

                    numberOfNightHoursLastWeek += 7;
                }

                //si finaliza en la madrugada se cuenta las horas desde las 8:00 P.M
                else if (calFin.get(Calendar.HOUR_OF_DAY) >= 1 && calFin.get(Calendar.HOUR_OF_DAY) <= 7) {

                    for (int k = calFin.get(Calendar.HOUR_OF_DAY); k >= 1; k--) {
                        numberOfNightHoursLastWeek++;
                    }

                } else if (calFin.get(Calendar.HOUR_OF_DAY) >= 8 && calFin.get(Calendar.HOUR_OF_DAY) <= 20) {
                    numberOfNightHoursLastWeek += 7;
                }
            }

            HorasNocturnas.add(numberOfNightHoursLastWeek);

        }

        System.out.println("horas nocturnas:" + HorasNocturnas);

        return HorasNocturnas;

    }

    //Horas normales: Horas trabajadas de lunes a sábado entre las 7:00 AM y las 8:00 PM
    public List<Integer> HorasNormales(Calendar calInicio, Calendar calFin, List<Integer> findWeeks, int numberOfDays) {

        int numberOfDaysFirstWeek = 0;
        int numberOfDaysLastWeek = 0;
        int numberOfHours = 0;
        List<Integer> HorasPorSemana = new ArrayList<>();

        //trabajó solo un día, Número de horas restantes en el dia, entre las 7:00 AM y las 8:00 PM
        if (findWeeks.size() == 1 && numberOfDays == 1) {

            if (calInicio.get(Calendar.HOUR_OF_DAY) >= 1 && calFin.get(Calendar.HOUR_OF_DAY) <= 20) {
                for (int i = 7; i < calFin.get(Calendar.HOUR_OF_DAY); i++) {
                    numberOfHours++;
                }
            } else if (calInicio.get(Calendar.HOUR_OF_DAY) >= 1 && calFin.get(Calendar.HOUR_OF_DAY) <= 24) {
                numberOfHours += 13;
            } else if (calInicio.get(Calendar.HOUR_OF_DAY) >= 7 && calFin.get(Calendar.HOUR_OF_DAY) <= 20) {
                for (int i = calInicio.get(Calendar.HOUR_OF_DAY); i < calFin.get(Calendar.HOUR_OF_DAY); i++) {
                    numberOfHours++;
                }
            }

            HorasPorSemana.add(numberOfHours);

        }


        //trabajó varios días o semanas
        if (findWeeks.size() == 1 && numberOfDays >= 2 && numberOfDays <= 6) {
            int contador = 0;
            int calc1 = 0;
            int calc2 = 0;

            if (calcularHorasDiaInicio(calInicio) != 0 && Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK)) {
                calc1 = calcularHorasDiaInicio(calInicio);
            }

            if (Calendar.SUNDAY != calFin.get(Calendar.DAY_OF_WEEK)) {
                calc2 = calcularHorasDiaFin(calFin);
            }

            if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK)) {
                for (int i = 1; i <= numberOfDays - 2; i++) {
                    contador += 13;
                }
            } else {
                for (int i = 1; i <= numberOfDays - 1; i++) {
                    contador += 13;
                }
            }


            HorasPorSemana.add(calc1 + calc2 + contador);


        }

        //si trabajo dos o más semanas
        if (findWeeks.size() >= 2) {

            //calcula el número de horas de la primera semana trabajada dependiendo del dia en que inició, calculo de dias que trabajo completos la primera semana, hasta el sabado.
            int compare = calInicio.get(Calendar.DAY_OF_MONTH);

            if (Calendar.SUNDAY != calInicio.get(Calendar.DAY_OF_WEEK)) {
                int delta = -calInicio.get(GregorianCalendar.DAY_OF_WEEK) + 1;
                calInicio.add(Calendar.DAY_OF_MONTH, delta);
                for (int i = 0; i < 6; i++) {
                    calInicio.add(Calendar.DAY_OF_MONTH, 1);

                    if (calInicio.get(Calendar.DAY_OF_MONTH) > compare) {
                        numberOfDaysFirstWeek++;
                    }

                }
                //los dias trabajados completos por las 13 horas de 7a.m-8p.m
                int totalHoras = 13 * numberOfDaysFirstWeek;
                HorasPorSemana.add(totalHoras + calcularHorasDiaInicio(calInicio));

            } else {
                //si inicia labores un domingo se toman los dias de lunes a viernes
                for (int i = 0; i < 6; i++) {
                    numberOfDaysFirstWeek++;
                }
                HorasPorSemana.add(numberOfDaysFirstWeek * 13);
            }

            //para las semanas que las trabajó completas de lunes a sábado por 13 horas, menos las 2 semanas de inicio y fin.

            for (int i = 1; i <= findWeeks.size() - 2; i++) {
                HorasPorSemana.add(6 * 13);
            }

            //calcula el número de horas de la última semana trabajada dependiendo del dia en que finalizó, calculo de dias que trabajo completos la última semana, contadas desde el lunes.
            if (Calendar.SUNDAY != calFin.get(Calendar.DAY_OF_WEEK)) {
                int compare2 = calFin.get(Calendar.DAY_OF_MONTH);

                int delta2 = -calFin.get(GregorianCalendar.DAY_OF_WEEK) + 1;
                calFin.add(Calendar.DAY_OF_MONTH, delta2);
                for (int i = 0; i < 6; i++) {
                    calFin.add(Calendar.DAY_OF_MONTH, 1);

                    if (calFin.get(Calendar.DAY_OF_MONTH) < compare2) {
                        numberOfDaysLastWeek++;
                    }

                }
                //los dias trabajados completos por las 13 horas de 7-8p.m

                int totalHoras2 = 13 * numberOfDaysLastWeek;
                HorasPorSemana.add(totalHoras2 + calcularHorasDiaFin(calFin));
            } else {
                HorasPorSemana.add(0);

            }

        }


        System.out.println("numero de dias primera semana: " + numberOfDaysFirstWeek);
        System.out.println("numero de dias ultima semana: " + numberOfDaysLastWeek);

        System.out.println("numero de dias: " + numberOfDays);
        System.out.println("Horas por semana: " + HorasPorSemana);

        return HorasPorSemana;
    }


    public int calcularDiasSinDomingos(Service service) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date date1;
        Date date2;
        try {
            date1 = df.parse(String.valueOf(service.getfInicio()));
            date2 = df.parse(String.valueOf(service.getfFin()));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        int numberOfDays = 1;
        while (cal1.before(cal2)) {
            if ((Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
            }
            cal1.add(Calendar.DATE, 1);
        }

        System.out.println("numero de dias:" + numberOfDays);
        return numberOfDays;
    }

    public int calcularHorasDiaInicio(Calendar calInicio) {

        int CalculoHorasTrabajadasPrimerDia = 0;

        //Dependiendo de la hora que inició a trabajar se le resta 20(8 p.m hora militar y límite de hora Normal) para obtener horas trabajadas ese dia.
        if (calInicio.get(Calendar.HOUR_OF_DAY) >= 7 && calInicio.get(Calendar.HOUR_OF_DAY) <= 20) {
            CalculoHorasTrabajadasPrimerDia = (20 - (calInicio.get(Calendar.HOUR_OF_DAY)));
        }

        //empieza en la madrugada, trabaja 13 horas de 7 a.m a 8 p.m
        else if (calInicio.get(Calendar.HOUR_OF_DAY) >= 1 && calInicio.get(Calendar.HOUR_OF_DAY) < 7) {
            CalculoHorasTrabajadasPrimerDia = 13;
        }

        return CalculoHorasTrabajadasPrimerDia;
    }

    public int calcularHorasDiaFin(Calendar calFin) {

        int CalculoHorasTrabajadasUltimoDia = 0;

        /*(desde las 7:00 a.m hasta las 8:00 p.m) restar 7 para obtener horas trabajadas desde las 7:00 a.m hasta la hora que finalizo trabajo ese dia. con límite hasta las 8:00 p.m. */
        if (calFin.get(Calendar.HOUR_OF_DAY) >= 7 && calFin.get(Calendar.HOUR_OF_DAY) <= 20) {

            CalculoHorasTrabajadasUltimoDia = (calFin.get(Calendar.HOUR_OF_DAY) - 7);
        }

        //finaliza a medianoche, trabaja 13 horas de 7 a.m a 8 p.m
        else if (calFin.get(Calendar.HOUR_OF_DAY) > 20 && calFin.get(Calendar.HOUR_OF_DAY) <= 24) {
            CalculoHorasTrabajadasUltimoDia = 13;
        }


        return CalculoHorasTrabajadasUltimoDia;
    }

    public String[] operation(Service service) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date fechaInicio;
        Date fechaFinal;
        String[] resultadosOperaciones = new String[7];

        try {
            fechaInicio = df.parse(String.valueOf(service.getfInicio()));
            fechaFinal = df.parse(String.valueOf(service.getfFin()));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        Calendar calInicio = Calendar.getInstance();
        Calendar calFin = Calendar.getInstance();

        calInicio.setTime(fechaInicio);
        calFin.setTime(fechaFinal);

        int week1 = calInicio.get(Calendar.WEEK_OF_YEAR);
        int week2 = calFin.get(Calendar.WEEK_OF_YEAR);

        //hallar la diferencia en dias
        int numeroDias = calcularDiasSinDomingos(service);


        List<Integer> findWeeks = getNumbersInRange(week1, week2);
        List<Integer> HorasNormales = HorasNormales(calInicio, calFin, findWeeks, numeroDias);
        List<Integer> HorasNocturnas = HorasNocturnas(calInicio, calFin, findWeeks, numeroDias);
        List<Integer> HorasDominicales = HorasDominicales(calInicio, calFin, findWeeks);
        List<Integer> NormalesExtras = HorasExtrasNormales(HorasNormales);
        List<Integer> NocturnasExtras = HorasExtrasNocturnas(HorasNormales, HorasNocturnas);
        List<Integer> DominicalesExtras = HorasExtrasDominicales(HorasNormales, HorasNocturnas, HorasDominicales);

        //System.out.println("semanas: " + findWeeks);

        //si horas por semana se pasa de las 48
        List<Integer> HorasNormalesPorSemana = new ArrayList<>();
        for (int i = 0; i < HorasNormales.size(); i++) {

            //si la hora normal ya es mayor a 48 en la semana se agregan las demás horas como extras.
            if (HorasNormales.get(i).intValue() > 48) {
                HorasNormalesPorSemana.add(48);
            } else {
                HorasNormalesPorSemana.add(HorasNormales.get(i));
            }
        }

        System.out.println("Horas por semana con limite: " + HorasNormalesPorSemana);

        resultadosOperaciones[0] = String.valueOf(findWeeks);
        resultadosOperaciones[1] = String.valueOf(HorasNormalesPorSemana);
        resultadosOperaciones[2] = String.valueOf(HorasNocturnas);
        resultadosOperaciones[3] = String.valueOf(HorasDominicales);
        resultadosOperaciones[4] = String.valueOf(NormalesExtras);
        resultadosOperaciones[5] = String.valueOf(NocturnasExtras);
        resultadosOperaciones[6] = String.valueOf(DominicalesExtras);


        return resultadosOperaciones;


    }


}
