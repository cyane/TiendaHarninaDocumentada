-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.19-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para tienda_harnina20189vistas
CREATE DATABASE IF NOT EXISTS `tienda_harnina20189vistas` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `tienda_harnina20189vistas`;

-- Volcando estructura para vista tienda_harnina20189vistas.carrito
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `carrito` (
	`IdCliente` INT(11) NOT NULL,
	`IdModelo` INT(11) NOT NULL,
	`cantidadPedida` TINYINT(4) NOT NULL,
	`actualPrecioModelo` FLOAT NOT NULL,
	`nombreModelo` VARCHAR(200) NOT NULL COLLATE 'utf8_unicode_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista tienda_harnina20189vistas.codigo_postal
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `codigo_postal` (
	`postalcode` VARCHAR(5) NOT NULL COLLATE 'utf8_unicode_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista tienda_harnina20189vistas.daper_cliente
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `daper_cliente` (
	`idCLient` INT(11) NOT NULL,
	`nif` VARCHAR(9) NOT NULL COLLATE 'utf8_unicode_ci',
	`lastname` VARCHAR(100) NOT NULL COLLATE 'utf8_unicode_ci',
	`firstname` VARCHAR(50) NOT NULL COLLATE 'utf8_unicode_ci',
	`postalCode` VARCHAR(5) NOT NULL COLLATE 'utf8_unicode_ci',
	`address` VARCHAR(100) NOT NULL COLLATE 'utf8_unicode_ci',
	`birthdate` DATE NOT NULL,
	`phone` VARCHAR(20) NOT NULL COLLATE 'utf8_unicode_ci',
	`mobile` VARCHAR(20) NOT NULL COLLATE 'utf8_unicode_ci',
	`sex` CHAR(1) NOT NULL COLLATE 'utf8_unicode_ci',
	`email` VARCHAR(150) NOT NULL COLLATE 'utf8_unicode_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista tienda_harnina20189vistas.factura_cabecera
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `factura_cabecera` (
	`numeroFactura` INT(11) NOT NULL,
	`nombreEmpresaFactura` VARCHAR(100) NOT NULL COLLATE 'utf8_unicode_ci',
	`domicilioEmpresaFactura` VARCHAR(200) NOT NULL COLLATE 'utf8_unicode_ci',
	`cifEmpresaFactura` VARCHAR(20) NOT NULL COLLATE 'utf8_unicode_ci',
	`nombreClienteFactura` VARCHAR(100) NOT NULL COLLATE 'utf8_unicode_ci',
	`domicilioClienteFactura` VARCHAR(200) NOT NULL COLLATE 'utf8_unicode_ci',
	`dniClienteFactura` VARCHAR(9) NOT NULL COLLATE 'utf8_unicode_ci',
	`fechaFacturacion` TIMESTAMP NOT NULL,
	`iva` TINYINT(4) NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista tienda_harnina20189vistas.factura_detalles
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `factura_detalles` (
	`numeroFactura` INT(11) NOT NULL,
	`idModelo` INT(11) NOT NULL,
	`nombreModelo` VARCHAR(200) NOT NULL COLLATE 'utf8_unicode_ci',
	`cantidadComprada` INT(11) NOT NULL,
	`precioModelo` INT(11) NOT NULL,
	`fechaInicioPedido` DATETIME NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista tienda_harnina20189vistas.login_cliente
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `login_cliente` (
	`idClient` INT(11) NOT NULL,
	`user` VARCHAR(30) NOT NULL COLLATE 'utf8_unicode_ci',
	`password` VARCHAR(50) NOT NULL COLLATE 'utf8_unicode_ci'
) ENGINE=MyISAM;

-- Volcando estructura para vista tienda_harnina20189vistas.phonemodels
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `phonemodels` (
	`IdModelo` INT(11) NOT NULL,
	`nombreModelo` VARCHAR(200) NOT NULL COLLATE 'utf8_unicode_ci',
	`RefModelo` VARCHAR(50) NOT NULL COLLATE 'utf8_unicode_ci',
	`actualPrecioModelo` FLOAT NOT NULL,
	`descripcionModelo` TEXT NOT NULL COLLATE 'utf8_unicode_ci',
	`NombreMarca` VARCHAR(50) NOT NULL COLLATE 'utf8_unicode_ci',
	`rutaImagen` VARCHAR(50) NOT NULL COLLATE 'utf8_unicode_ci',
	`rutaImagenBack` VARCHAR(50) NOT NULL COLLATE 'utf8_unicode_ci',
	`rutaImagenSide` VARCHAR(50) NOT NULL COLLATE 'utf8_unicode_ci',
	`stockActualModelo` INT(11) NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.blockClient
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `blockClient`(IN `idClient` INT, IN `clave` VARCHAR(50), OUT `mensa` BIGINT)
    NO SQL
BEGIN

INSERT INTO tienda_harnina20189.`clientlocked`(`idClient`, `clave`) VALUES (idClient,clave);


IF ROW_COUNT() > 0 THEN
	SET mensa = true;
ELSE
	SET mensa = false;
END IF;

END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.check_cp
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_cp`(IN `postalCode` VARCHAR(5), OUT `retorno` VARCHAR(5))
    NO SQL
BEGIN

SELECT codigo_postal.postalCode INTO retorno FROM codigo_postal WHERE codigo_postal.postalCode = postalCode;

END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.deleteCarrito
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCarrito`(
	IN `idClient` INT,
	OUT `mensa` BOOLEAN
)
BEGIN
DELETE from tienda_harnina20189.carrito where tienda_harnina20189.carrito.idCliente = idClient;

IF ROW_COUNT() > 0 THEN
  SET mensa = true;
ELSE
  SET mensa = false;
END IF;
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.deleteClient
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteClient`(IN `idClient` INT, OUT `mensa` BOOLEAN)
    NO SQL
BEGIN

INSERT INTO tienda_harnina20189.`clientlocked`(`idClient`, `clave`) VALUES (idClient,clave);

IF ROW_COUNT() > 0 THEN
	SET mensa = true;
ELSE
	SET mensa = false;
END IF;

END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.getCarrito
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCarrito`(IN `IdCliente` INT)
BEGIN
select * from carrito where carrito.idCliente = IdCliente;
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.getCliente
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCliente`(IN `idCLient` INT)
BEGIN
SELECT daper_cliente.* FROM daper_cliente where daper_cliente.idClient = idClient; 
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.getClienteLogin
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getClienteLogin`(IN `idClient` INT)
BEGIN
SELECT login_cliente.* FROM login_cliente where login_cliente.idClient=idClient; 
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.getFacturasCabeceras
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getFacturasCabeceras`(
	IN `idClient` INT


)
BEGIN
Declare dni varchar(9) default (select nif from daper_cliente where daper_cliente.idCLient = idClient);
select * from factura_cabecera where factura_cabecera.DniClienteFactura = dni;
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.getFacturasDetalles
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getFacturasDetalles`(
	IN `numeroFactura` INT
)
BEGIN
select * from factura_detalles where factura_detalles.numeroFactura = numeroFactura;
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.getIdLogin
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdLogin`(IN `userName` VARCHAR(30), IN `userPassword` VARCHAR(50), OUT `mensa` VARCHAR(9))
    NO SQL
BEGIN
SELECT login_cliente.idClient into mensa FROM login_cliente WHERE login_cliente.user = userName and login_cliente.password = md5(userPassword);
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.getListaClientes
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getListaClientes`()
    NO SQL
BEGIN
select daper_cliente.* from daper_cliente;
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.getPhoneModels
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getPhoneModels`(IN `inicioCache` INT, IN `cantidadCache` INT)
    NO SQL
BEGIN

DECLARE inicio int DEFAULT 0;
DECLARE cantidad int DEFAULT 0;

SET inicio = inicioCache;
SET cantidad = cantidadCache + inicioCache;
select * from phonemodels LIMIT cantidad OFFSET inicioCache;
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.get_cp
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_cp`()
    NO SQL
BEGIN
SELECT postalCode FROM codigo_postal;
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.guardarCarrito
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `guardarCarrito`(
	IN `IdCliente` INT,
	IN `IdModelo` INT,
	IN `cantidadPedida` INT,
	OUT `mensa` VARCHAR(50)





)
BEGIN

INSERT INTO tienda_harnina20189.carrito (`idCliente`, `IdModelo`, `cantidadPedida`) VALUES (IdCliente,IdModelo,cantidadPedida);

IF ROW_COUNT() > 0 THEN
	SET mensa = true;
ELSE
	SET mensa = false;
END IF;

END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.hacerFactura
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `hacerFactura`(
	IN `idClient` INT


,
	OUT `mensaje` VARCHAR(2000)

)
BEGIN
call tienda_harnina20189.generarFacturaV2(idClient,@a);
set mensaje = @a;
END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.insertClient
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertClient`(IN `nif` VARCHAR(9), IN `lastname` VARCHAR(100), IN `firstname` VARCHAR(50), IN `postalCode` VARCHAR(5), IN `address` VARCHAR(100), IN `birthDate` DATE, IN `phone` VARCHAR(20), IN `mobile` VARCHAR(20), IN `sex` CHAR(1), IN `email` VARCHAR(150), IN `userName` VARCHAR(30), IN `UserPassword` VARCHAR(50), OUT `mensa` BOOLEAN)
    NO SQL
BEGIN

INSERT INTO `tienda_harnina20189`.`client`(`nif`, `lastname`, `firstname`, `postalCode`, `address`, `birthdate`, `phone`, `mobile`, `sex`, `email`, `user`, `password`) VALUES (nif,lastname,firstname,postalCode,address,birthDate,phone,mobile,sex,email,userName,md5(UserPassword));

IF ROW_COUNT() > 0 THEN
	SET mensa = true;
ELSE
	SET mensa = false;
END IF;

END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.isLocked
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `isLocked`(IN `idCLient` INT, OUT `retorno` BIGINT)
    NO SQL
BEGIN

SELECT tienda_harnina20189.`clientlocked`.idClient INTO retorno FROM tienda_harnina20189.`clientlocked` WHERE tienda_harnina20189.`clientlocked`.idClient = idClient;

END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.unblockClient
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `unblockClient`(IN `idClient` INT, OUT `mensa` BIGINT)
    NO SQL
BEGIN

DELETE FROM tienda_harnina20189.`clientlocked` WHERE tienda_harnina20189.`clientlocked`.nidClientif = idClient; 


IF ROW_COUNT() > 0 THEN
	SET mensa = true;
ELSE
	SET mensa = false;
END IF;

END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.updateClientDaper
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateClientDaper`(IN `lastname` VARCHAR(100), IN `firstname` VARCHAR(50), IN `postalCode` VARCHAR(5), IN `address` VARCHAR(100), IN `birthdate` DATE, IN `mobile` VARCHAR(20), IN `phone` VARCHAR(20), IN `sex` CHAR(1), IN `email` VARCHAR(150), IN `nif` VARCHAR(9), IN `userName` VARCHAR(30), OUT `mensa` BOOLEAN)
BEGIN

UPDATE tienda_harnina20189.client SET client.nif = nif,client.lastname = lastname,client.firstname=firstname,client.postalCode=postalCode,client.address=address,client.birthdate=birthdate,client.phone=phone,client.mobile=mobile,client.sex=sex,client.email=email WHERE client.user = userName;

IF ROW_COUNT() > 0 THEN SET mensa = true;
   ELSE SET mensa =  false;
END IF; 

END//
DELIMITER ;

-- Volcando estructura para procedimiento tienda_harnina20189vistas.updateLogin
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateLogin`(IN `idClient` INT, IN `userName` VARCHAR(100), IN `userPassword` VARCHAR(50), OUT `mensa` BOOLEAN)
    NO SQL
BEGIN

UPDATE tienda_harnina20189.client SET client.user = userName,client.passWord = md5(userPassword) WHERE client.idClient = idClient;

IF ROW_COUNT() > 0 THEN SET mensa = true;
   ELSE SET mensa =  false;
END IF; 

END//
DELIMITER ;

-- Volcando estructura para vista tienda_harnina20189vistas.carrito
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `carrito`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `carrito` AS SELECT tienda_harnina20189.carrito.idCliente as IdCliente, tienda_harnina20189.carrito.IdModelo as IdModelo, tienda_harnina20189.carrito.CantidadPedida as cantidadPedida,tienda_harnina20189.modelo.actualPrecioModelo as actualPrecioModelo, tienda_harnina20189.modelo.nombreModelo as nombreModelo FROM tienda_harnina20189.carrito ,tienda_harnina20189.modelo WHERE tienda_harnina20189.carrito.IdModelo = tienda_harnina20189.modelo.IdModelo ;

-- Volcando estructura para vista tienda_harnina20189vistas.codigo_postal
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `codigo_postal`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `codigo_postal` AS select `tienda_harnina20189`.`postalcode`.`postalCode` AS `postalcode` from `tienda_harnina20189`.`postalcode` ;

-- Volcando estructura para vista tienda_harnina20189vistas.daper_cliente
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `daper_cliente`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `daper_cliente` AS select `tienda_harnina20189`.`client`.`id` AS `idCLient`,`tienda_harnina20189`.`client`.`nif` AS `nif`,`tienda_harnina20189`.`client`.`lastname` AS `lastname`,`tienda_harnina20189`.`client`.`firstname` AS `firstname`,`tienda_harnina20189`.`client`.`postalCode` AS `postalCode`,`tienda_harnina20189`.`client`.`address` AS `address`,`tienda_harnina20189`.`client`.`birthdate` AS `birthdate`,`tienda_harnina20189`.`client`.`phone` AS `phone`,`tienda_harnina20189`.`client`.`mobile` AS `mobile`,`tienda_harnina20189`.`client`.`sex` AS `sex`,`tienda_harnina20189`.`client`.`email` AS `email` from `tienda_harnina20189`.`client` ;

-- Volcando estructura para vista tienda_harnina20189vistas.factura_cabecera
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `factura_cabecera`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `factura_cabecera` AS SELECT fc.NumeroFactura AS numeroFactura, 
fc.NombreEmpresaFactura AS nombreEmpresaFactura, fc.DomicilioEmpresaFactura AS domicilioEmpresaFactura, fc.cifEmpresaFactura AS cifEmpresaFactura, fc.NombreClienteFactura AS nombreClienteFactura, fc.DomicilioClienteFactura AS domicilioClienteFactura, fc.DniClienteFactura AS dniClienteFactura, fc.FechaFacturacion AS fechaFacturacion, fc.Iva AS iva FROM tienda_harnina20189.factura_cabecera fc where 1 ;

-- Volcando estructura para vista tienda_harnina20189vistas.factura_detalles
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `factura_detalles`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `factura_detalles` AS SELECT fd.NumeroFactura as numeroFactura, fd.IdModelo AS idModelo, fd.NombreModeloDetalle AS nombreModelo, fd.CantidadCompradaDetalle AS cantidadComprada, fd.PrecioModeloDetalle AS precioModelo, fd.fechaInicioPedido AS fechaInicioPedido from tienda_harnina20189.factura_detalle fd where 1 ;

-- Volcando estructura para vista tienda_harnina20189vistas.login_cliente
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `login_cliente`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `login_cliente` AS select `tienda_harnina20189`.`client`.`id` AS `idClient`,`tienda_harnina20189`.`client`.`user` AS `user`,`tienda_harnina20189`.`client`.`password` AS `password` from `tienda_harnina20189`.`client` ;

-- Volcando estructura para vista tienda_harnina20189vistas.phonemodels
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `phonemodels`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `phonemodels` AS select `tienda_harnina20189`.`modelo`.`IdModelo` AS `IdModelo`,`tienda_harnina20189`.`modelo`.`nombreModelo` AS `nombreModelo`,`tienda_harnina20189`.`modelo`.`RefModelo` AS `RefModelo`,`tienda_harnina20189`.`modelo`.`actualPrecioModelo` AS `actualPrecioModelo`,`tienda_harnina20189`.`modelo`.`descripcionModelo` AS `descripcionModelo`,`tienda_harnina20189`.`marca`.`NombreMarca` AS `NombreMarca`,`tienda_harnina20189`.`modelo`.`ImageModel` AS `rutaImagen`,`tienda_harnina20189`.`modelo`.`ImageModelBack` AS `rutaImagenBack`,`tienda_harnina20189`.`modelo`.`ImageModelSide` AS `rutaImagenSide`,`tienda_harnina20189`.`modelo`.`stockActualModelo` AS `stockActualModelo` from (`tienda_harnina20189`.`modelo` join `tienda_harnina20189`.`marca`) where (`tienda_harnina20189`.`modelo`.`IdMarca` = `tienda_harnina20189`.`marca`.`IdMarca`) ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
