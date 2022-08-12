-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-08-2022 a las 16:44:51
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `handyman`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calculo`
--

CREATE TABLE `calculo` (
  `id` int(11) NOT NULL,
  `semanas` varchar(250) NOT NULL,
  `idTecnico` varchar(20) NOT NULL,
  `horasNormales` varchar(250) NOT NULL,
  `horasNocturnas` varchar(250) NOT NULL,
  `horasDominicales` varchar(250) NOT NULL,
  `horasNormalesExtras` varchar(250) NOT NULL,
  `horasNocturnasExtras` varchar(250) NOT NULL,
  `horasDominicalesExtras` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `calculo`
--

INSERT INTO `calculo` (`id`, `semanas`, `idTecnico`, `horasNormales`, `horasNocturnas`, `horasDominicales`, `horasNormalesExtras`, `horasNocturnasExtras`, `horasDominicalesExtras`) VALUES
(1, '31', '123', '26', '22', '0', '13', '5', '0'),
(2, '32', '456', '44', '0', '0', '0', '0', '0'),
(3, '33,34', '890', '13', '108', '0', '13', '-12', '17'),
(4, '33', '567567', '22', '0', '11', '0', '0', '0'),
(5, '31', '1080186785', '26', '22', '0', '13', '5', '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id` int(11) NOT NULL,
  `idTecnico` varchar(50) NOT NULL,
  `idServicio` varchar(50) NOT NULL,
  `fechaInicio` datetime NOT NULL,
  `fechaFin` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id`, `idTecnico`, `idServicio`, `fechaInicio`, `fechaFin`) VALUES
(1, '123', '456', '2022-07-25 01:00:00', '2022-07-27 23:00:00'),
(2, '456', '234', '2022-08-03 08:08:00', '2022-08-06 19:08:00'),
(3, '890', '567', '2022-08-07 04:16:00', '2022-08-14 20:17:00'),
(4, '567567', '12312', '2022-08-07 07:45:00', '2022-08-08 18:45:00'),
(5, '1080186785', '55111', '2022-07-25 01:00:00', '2022-07-27 23:00:00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `calculo`
--
ALTER TABLE `calculo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idTecnico` (`idTecnico`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idTecnico` (`idTecnico`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `calculo`
--
ALTER TABLE `calculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `calculo`
--
ALTER TABLE `calculo`
  ADD CONSTRAINT `tecnico_fk` FOREIGN KEY (`idTecnico`) REFERENCES `servicio` (`idTecnico`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
