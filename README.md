# HandyManBackend

calculadora donde recibirá el reporte de los técnicos y entregará por medio de una consulta, para un técnico, cuál es su calculo de
horas trabajadas por semana.

Funcionalidades
- Reporte de atención a servicio por técnico

Reglas de negocio
Campos de un reporte de servicio:
- Identificación del técnico.
- Identificación del servicio.
- Fecha y hora de inicio.
- Fecha y hora de fin.

Servicio REST que reciba el reporte, haga las respectivas validaciones, almacene
la información en alguna fuente de datos y retorna el resultado de la operación.

Reglas de negocio
- Presentar el cálculo de horas de trabajo para el técnico en función de las siguientes
combinaciones:
- Horas normales: Horas trabajadas de lunes a sábado entre las 7:00 AM y las
8:00 PM
- Horas nocturnas: Horas trabajadas de lunes a sábado entre las 8:00 PM y las
7:00 AM
- Horas Dominicales: Horas trabajadas el día Domingo.
- Horas Normales Extra: Horas trabajadas de lunes a sábado entre las 7:00
AM y las 8:00 PM después de que el técnico ya ha trabajado 48 horas esa
semana.
- Horas nocturnas Extra: Horas trabajadas de lunes a sábado entre las 8:00
PM y las 7:00 AM después de que el técnico ya ha trabajado 48 horas esa
semana.
- Horas Dominicales Extra: Horas trabajadas el día Domingo después de que
el técnico ya ha trabajado 48 horas esa semana.
