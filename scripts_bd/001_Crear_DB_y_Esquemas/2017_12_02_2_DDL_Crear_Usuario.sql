-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
--@uthor: Miguel Leonardo Hernandez
--Date: 30-11-2017
--Description: Creación de usuario con superusuario
-------------------------------------------------------------------------------

CREATE USER adminstore WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'tienda1234';
COMMENT ON ROLE adminstore IS 'Usuario de aplicacion compras_api';