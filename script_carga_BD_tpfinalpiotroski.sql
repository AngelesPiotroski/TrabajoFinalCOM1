-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-08-2021 a las 03:17:16
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tpfinalpiotroski`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `IDPERSONA` bigint(20) NOT NULL,
  `APELLIDO` varchar(255) DEFAULT NULL,
  `CARGO` varchar(255) DEFAULT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `DNI` bigint(20) DEFAULT NULL,
  `FECHANAC` date DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `USUARIO_NROUSUARIO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`IDPERSONA`, `APELLIDO`, `CARGO`, `DIRECCION`, `DNI`, `FECHANAC`, `NOMBRE`, `USUARIO_NROUSUARIO`) VALUES
(213, 'admin', 'admin', '213', 12333, '2021-08-04', 'admin', 123);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado_reserva`
--

CREATE TABLE `empleado_reserva` (
  `Empleado_IDPERSONA` bigint(20) NOT NULL,
  `reservas_NRORESERVA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleado_reserva`
--

INSERT INTO `empleado_reserva` (`Empleado_IDPERSONA`, `reservas_NRORESERVA`) VALUES
(213, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `habitacion` (
  `NROHABITACION` int(11) NOT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `PISO` int(11) DEFAULT NULL,
  `PRECIONOCHE` float DEFAULT NULL,
  `TIPO_NROTIPO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`NROHABITACION`, `NOMBRE`, `PISO`, `PRECIONOCHE`, `TIPO_NROTIPO`) VALUES
(2, 'simple', 1, 456, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion_reserva`
--

CREATE TABLE `habitacion_reserva` (
  `Habitacion_NROHABITACION` int(11) NOT NULL,
  `reserva_NRORESERVA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `habitacion_reserva`
--

INSERT INTO `habitacion_reserva` (`Habitacion_NROHABITACION`, `reserva_NRORESERVA`) VALUES
(2, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `huesped`
--

CREATE TABLE `huesped` (
  `IDPERSONA` bigint(20) NOT NULL,
  `APELLIDO` varchar(255) DEFAULT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `DNI` bigint(20) DEFAULT NULL,
  `FECHANAC` date DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `PROFESION` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `huesped`
--

INSERT INTO `huesped` (`IDPERSONA`, `APELLIDO`, `DIRECCION`, `DNI`, `FECHANAC`, `NOMBRE`, `PROFESION`) VALUES
(3, 'Lopez', 'calle 124 casa 678', 43234567, '1999-05-04', 'Damian', 'ingeniero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `huesped_reserva`
--

CREATE TABLE `huesped_reserva` (
  `Huesped_IDPERSONA` bigint(20) NOT NULL,
  `reservas_NRORESERVA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `huesped_reserva`
--

INSERT INTO `huesped_reserva` (`Huesped_IDPERSONA`, `reservas_NRORESERVA`) VALUES
(3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `IDPERSONA` bigint(20) NOT NULL,
  `APELLIDO` varchar(255) DEFAULT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `DNI` bigint(20) DEFAULT NULL,
  `FECHANAC` date DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `NRORESERVA` int(11) NOT NULL,
  `CHECKIN` date DEFAULT NULL,
  `CHECKOUT` date DEFAULT NULL,
  `EMPLEADO` longblob DEFAULT NULL,
  `FECHACREACION` date DEFAULT NULL,
  `HABITACION_NROHABITACION` int(11) DEFAULT NULL,
  `HUESPED_IDPERSONA` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`NRORESERVA`, `CHECKIN`, `CHECKOUT`, `EMPLEADO`, `FECHACREACION`, `HABITACION_NROHABITACION`, `HUESPED_IDPERSONA`) VALUES
(4, '2021-08-09', '2021-08-10', 0xaced00057372000f4c6f676963612e456d706c6561646fe52ea7106686bb810200034c0005636172676f7400124c6a6176612f6c616e672f537472696e673b4c000872657365727661737400104c6a6176612f7574696c2f4c6973743b4c00077573756172696f7400104c4c6f676963612f5573756172696f3b7872000e4c6f676963612e506572736f6e61dfc08b3a8e89e0ce0200064c00086170656c6c69646f71007e00014c0009646972656363696f6e71007e00014c0003646e697400104c6a6176612f6c616e672f4c6f6e673b4c000866656368614e61637400104c6a6176612f7574696c2f446174653b4c00096964506572736f6e6171007e00054c00066e6f6d62726571007e0001787074000561646d696e7400033231337372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000000000302d7372000e6a6176612e7574696c2e44617465686a81014b597419030000787077080000017b0f1a7f80787371007e000a00000000000000d574000561646d696e74000561646d696e737200306f72672e65636c697073652e70657273697374656e63652e696e646972656374696f6e2e496e6469726563744c69737421188b8a9d256db002000649000f696e697469616c43617061636974795a001569734c6973744f7264657242726f6b656e496e44625a000c6973526567697374657265645a00147573654c617a79496e7374616e74696174696f6e4c000864656c65676174657400124c6a6176612f7574696c2f566563746f723b4c000b76616c7565486f6c64657274003a4c6f72672f65636c697073652f70657273697374656e63652f696e646972656374696f6e2f56616c7565486f6c646572496e746572666163653b787200106a6176612e7574696c2e566563746f72d9977d5b803baf010300034900116361706163697479496e6372656d656e7449000c656c656d656e74436f756e745b000b656c656d656e74446174617400135b4c6a6176612f6c616e672f4f626a6563743b78700000000000000000757200135b4c6a6176612e6c616e672e4f626a6563743b90ce589f1073296c02000078700000000078000000010000017371007e001500000000000000017571007e0018000000017372000e4c6f676963612e5265736572766132fdbbc433e590e302000749000a6e726f526573657276614c0007636865636b496e71007e00064c0008636865636b4f757471007e00064c0008656d706c6561646f7400114c4c6f676963612f456d706c6561646f3b4c000d66656368614372656163696f6e71007e00064c000a68616269746163696f6e7400134c4c6f676963612f48616269746163696f6e3b4c0007687565737065647400104c4c6f676963612f487565737065643b7870000000047371007e000d77080000017b28da4b80787371007e000d77080000017b2e00a7807871007e00077371007e000d77080000017b2da176e678737200114c6f676963612e48616269746163696f6e4707af577a0a76db02000649000d6e726f48616269746163696f6e4900047069736f46000b70726563696f4e6f6368654c00066e6f6d62726571007e00014c00077265736572766171007e00024c00047469706f7400174c4c6f676963612f5469706f48616269746163696f6e3b7870000000020000000143e4000074000673696d706c657371007e001200000000000000007571007e00180000000078000000010000017371007e001500000000000000017571007e00180000000171007e0020787372002f6f72672e65636c697073652e70657273697374656e63652e696e646972656374696f6e2e56616c7565486f6c646572c91e7740c4d4d30e0200035a00196973436f6f7264696e617465645769746850726f70657274795a001869734e65776c7957656176656456616c7565486f6c6465724c000576616c75657400124c6a6176612f6c616e672f4f626a6563743b7870000071007e002a737200154c6f676963612e5469706f48616269746163696f6e71c61f933958a64802000549001063616e7469646164506572736f6e61734900076e726f5469706f4c000b6465736372697063696f6e71007e00014c000c68616269746163696f6e657371007e00024c00066e6f6d62726571007e00017870000000010000000174000c3120646f726d69746f72696f7371007e001200000000000000007571007e001800000000780000000a00010170737200476f72672e65636c697073652e70657273697374656e63652e696e7465726e616c2e696e646972656374696f6e2e556e69744f66576f726b517565727956616c7565486f6c646572a57491b93c50ee6e020000787200426f72672e65636c697073652e70657273697374656e63652e696e7465726e616c2e696e646972656374696f6e2e556e69744f66576f726b56616c7565486f6c6465727ad96ecbfa8681180200054c00116261636b757056616c7565486f6c6465727400314c6f72672f65636c697073652f70657273697374656e63652f696e646972656374696f6e2f56616c7565486f6c6465723b4c001072656d6f7465556e69744f66576f726b74003a4c6f72672f65636c697073652f70657273697374656e63652f696e7465726e616c2f73657373696f6e732f556e69744f66576f726b496d706c3b4c0013736f757263654174747269627574654e616d6571007e00014c000c736f757263654f626a65637471007e002d4c001a7772617070656456616c7565486f6c64657252656d6f746549447400174c6a6176612f726d692f7365727665722f4f626a49443b787200406f72672e65636c697073652e70657273697374656e63652e696e7465726e616c2e696e646972656374696f6e2e446174616261736556616c7565486f6c646572d251d6860f91d1ec0200045a00196973436f6f7264696e617465645769746850726f70657274795a000e6973496e7374616e7469617465644c0003726f7774003a4c6f72672f65636c697073652f70657273697374656e63652f696e7465726e616c2f73657373696f6e732f41627374726163745265636f72643b4c000576616c756571007e002d7870000070707372003e6f72672e65636c697073652e70657273697374656e63652e696e7465726e616c2e696e646972656374696f6e2e4261636b757056616c7565486f6c6465727232fb297395c5480200014c0015756e69744f66576f726b56616c7565486f6c64657271007e00147871007e002c00007071007e003b7074000c68616269746163696f6e6573707074000673696e676c657372000e4c6f676963612e487565737065643fc194cd9aeefe930200024c000970726f666573696f6e71007e00014c0008726573657276617371007e00027871007e00047400054c6f70657a74001263616c6c65203132342063617361203637387371007e000a000000000293b5077371007e000d7708000000d78d317f80787371007e000a000000000000000374000644616d69616e740009696e67656e6965726f7371007e001200000000000000007571007e00180000000078000000010000017371007e001500000000000000017571007e00180000000171007e0020787371007e002c000071007e004b787371007e002c000071007e001a7372000e4c6f676963612e5573756172696f0e0b08583739ba9602000349000a6e726f5573756172696f4c000a636f6e74726173656e6171007e00014c000d6e6f6d6272655573756172696f71007e000178700000007b74000561646d696e74000f61646d696e40676d61696c2e636f6d, '2021-08-09', 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '50');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipohabitacion`
--

CREATE TABLE `tipohabitacion` (
  `NROTIPO` int(11) NOT NULL,
  `CANTIDADPERSONAS` int(11) DEFAULT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipohabitacion`
--

INSERT INTO `tipohabitacion` (`NROTIPO`, `CANTIDADPERSONAS`, `DESCRIPCION`, `NOMBRE`) VALUES
(1, 1, '1 dormitorio', 'single');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipohabitacion_habitacion`
--

CREATE TABLE `tipohabitacion_habitacion` (
  `TipoHabitacion_NROTIPO` int(11) NOT NULL,
  `habitaciones_NROHABITACION` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipohabitacion_habitacion`
--

INSERT INTO `tipohabitacion_habitacion` (`TipoHabitacion_NROTIPO`, `habitaciones_NROHABITACION`) VALUES
(1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `NROUSUARIO` int(11) NOT NULL,
  `CONTRASENA` varchar(255) DEFAULT NULL,
  `NOMBREUSUARIO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`NROUSUARIO`, `CONTRASENA`, `NOMBREUSUARIO`) VALUES
(123, 'admin', 'admin@gmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`IDPERSONA`),
  ADD KEY `FK_EMPLEADO_USUARIO_NROUSUARIO` (`USUARIO_NROUSUARIO`);

--
-- Indices de la tabla `empleado_reserva`
--
ALTER TABLE `empleado_reserva`
  ADD PRIMARY KEY (`Empleado_IDPERSONA`,`reservas_NRORESERVA`),
  ADD KEY `FK_EMPLEADO_RESERVA_reservas_NRORESERVA` (`reservas_NRORESERVA`);

--
-- Indices de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`NROHABITACION`),
  ADD KEY `FK_HABITACION_TIPO_NROTIPO` (`TIPO_NROTIPO`);

--
-- Indices de la tabla `habitacion_reserva`
--
ALTER TABLE `habitacion_reserva`
  ADD PRIMARY KEY (`Habitacion_NROHABITACION`,`reserva_NRORESERVA`),
  ADD KEY `FK_HABITACION_RESERVA_reserva_NRORESERVA` (`reserva_NRORESERVA`);

--
-- Indices de la tabla `huesped`
--
ALTER TABLE `huesped`
  ADD PRIMARY KEY (`IDPERSONA`);

--
-- Indices de la tabla `huesped_reserva`
--
ALTER TABLE `huesped_reserva`
  ADD PRIMARY KEY (`Huesped_IDPERSONA`,`reservas_NRORESERVA`),
  ADD KEY `FK_HUESPED_RESERVA_reservas_NRORESERVA` (`reservas_NRORESERVA`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`IDPERSONA`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`NRORESERVA`),
  ADD KEY `FK_RESERVA_HABITACION_NROHABITACION` (`HABITACION_NROHABITACION`),
  ADD KEY `FK_RESERVA_HUESPED_IDPERSONA` (`HUESPED_IDPERSONA`);

--
-- Indices de la tabla `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Indices de la tabla `tipohabitacion`
--
ALTER TABLE `tipohabitacion`
  ADD PRIMARY KEY (`NROTIPO`);

--
-- Indices de la tabla `tipohabitacion_habitacion`
--
ALTER TABLE `tipohabitacion_habitacion`
  ADD PRIMARY KEY (`TipoHabitacion_NROTIPO`,`habitaciones_NROHABITACION`),
  ADD KEY `TIPOHABITACIONHABITACIONhabitaciones_NROHABITACION` (`habitaciones_NROHABITACION`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`NROUSUARIO`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `FK_EMPLEADO_USUARIO_NROUSUARIO` FOREIGN KEY (`USUARIO_NROUSUARIO`) REFERENCES `usuario` (`NROUSUARIO`);

--
-- Filtros para la tabla `empleado_reserva`
--
ALTER TABLE `empleado_reserva`
  ADD CONSTRAINT `FK_EMPLEADO_RESERVA_Empleado_IDPERSONA` FOREIGN KEY (`Empleado_IDPERSONA`) REFERENCES `empleado` (`IDPERSONA`),
  ADD CONSTRAINT `FK_EMPLEADO_RESERVA_reservas_NRORESERVA` FOREIGN KEY (`reservas_NRORESERVA`) REFERENCES `reserva` (`NRORESERVA`);

--
-- Filtros para la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD CONSTRAINT `FK_HABITACION_TIPO_NROTIPO` FOREIGN KEY (`TIPO_NROTIPO`) REFERENCES `tipohabitacion` (`NROTIPO`);

--
-- Filtros para la tabla `habitacion_reserva`
--
ALTER TABLE `habitacion_reserva`
  ADD CONSTRAINT `FK_HABITACION_RESERVA_Habitacion_NROHABITACION` FOREIGN KEY (`Habitacion_NROHABITACION`) REFERENCES `habitacion` (`NROHABITACION`),
  ADD CONSTRAINT `FK_HABITACION_RESERVA_reserva_NRORESERVA` FOREIGN KEY (`reserva_NRORESERVA`) REFERENCES `reserva` (`NRORESERVA`);

--
-- Filtros para la tabla `huesped_reserva`
--
ALTER TABLE `huesped_reserva`
  ADD CONSTRAINT `FK_HUESPED_RESERVA_Huesped_IDPERSONA` FOREIGN KEY (`Huesped_IDPERSONA`) REFERENCES `huesped` (`IDPERSONA`),
  ADD CONSTRAINT `FK_HUESPED_RESERVA_reservas_NRORESERVA` FOREIGN KEY (`reservas_NRORESERVA`) REFERENCES `reserva` (`NRORESERVA`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `FK_RESERVA_HABITACION_NROHABITACION` FOREIGN KEY (`HABITACION_NROHABITACION`) REFERENCES `habitacion` (`NROHABITACION`),
  ADD CONSTRAINT `FK_RESERVA_HUESPED_IDPERSONA` FOREIGN KEY (`HUESPED_IDPERSONA`) REFERENCES `huesped` (`IDPERSONA`);

--
-- Filtros para la tabla `tipohabitacion_habitacion`
--
ALTER TABLE `tipohabitacion_habitacion`
  ADD CONSTRAINT `TIPOHABITACIONHABITACIONhabitaciones_NROHABITACION` FOREIGN KEY (`habitaciones_NROHABITACION`) REFERENCES `habitacion` (`NROHABITACION`),
  ADD CONSTRAINT `TIPOHABITACION_HABITACION_TipoHabitacion_NROTIPO` FOREIGN KEY (`TipoHabitacion_NROTIPO`) REFERENCES `tipohabitacion` (`NROTIPO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
