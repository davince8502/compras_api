-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
--@uthor: Miguel Leonardo Hernandez
--Date: 30-11-2017
--Description: Creación de la base de datos Compras Tienda con superusuario
-------------------------------------------------------------------------------

CREATE DATABASE compras_tienda
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;