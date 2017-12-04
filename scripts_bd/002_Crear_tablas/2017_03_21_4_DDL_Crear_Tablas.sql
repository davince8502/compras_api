-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
--@uthor: Miguel Leonardo Hernandez
--Date: 30-11-2017
--Description: Creación de tablas esquema inicial
-------------------------------------------------------------------------------

-- DO $$ DECLARE
    -- tabname RECORD;
-- BEGIN
    -- FOR tabname IN (SELECT tablename 
                    -- FROM pg_tables 
                    -- WHERE schemaname = 'compras_tienda') 
-- LOOP
    -- EXECUTE 'DROP TABLE IF EXISTS compras_tienda.' || quote_ident(tabname.tablename) || ' CASCADE';
-- END LOOP;
-- END $$;





-- FUNCTION: compras_tienda.actualiza_fecha_modificacion()

-- DROP FUNCTION IF EXISTS compras_tienda.actualiza_fecha_modificacion() CASCADE;

CREATE FUNCTION compras_tienda.actualiza_fecha_modificacion()
    RETURNS trigger
    LANGUAGE 'plpgsql'
	AS $BODY$
	BEGIN
		NEW.modificado_en := now();
		RETURN NEW;
	END;
$BODY$;

ALTER FUNCTION compras_tienda.actualiza_fecha_modificacion()
    OWNER TO adminstore;

COMMENT ON FUNCTION compras_tienda.actualiza_fecha_modificacion()
    IS 'Actualizar fecha modificación';

	
	
	
-- Table: compras_tienda.usuario

-- DROP TABLE compras_tienda.usuario;	
	
CREATE TABLE compras_tienda.usuario (
                id bigserial NOT NULL,
				nombres VARCHAR(255) NOT NULL,
				primer_apellido character varying(20) NOT NULL,
				segundo_apellido character varying(20),
                birthday timestamp without time zone,
                email VARCHAR(255),
				username character varying(30) NOT NULL,
				password VARCHAR(255) NOT NULL,
                estado BIGINT DEFAULT 1 NOT NULL,
				numero_documento character varying(12) NOT NULL,
				id_tipo_documento bigint NOT NULL,
                fullname VARCHAR(255),
                genero BIGINT DEFAULT 1,
				cambio_clave boolean NOT NULL DEFAULT false,
				telefono character varying(25),                
				creado_por BIGINT,
                creado_en timestamp without time zone DEFAULT now(),
                modificado_por BIGINT,
				modificado_en timestamp without time zone DEFAULT now(),
                CONSTRAINT usuarios_pk PRIMARY KEY (id),
				UNIQUE (username),
				UNIQUE (numero_documento),
				UNIQUE (email)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE compras_tienda.usuario
    OWNER to adminstore;

COMMENT ON TABLE compras_tienda.usuario
    IS 'Tabla de Usuarios';
	
-- Trigger: fecha_modificacion_usuario

-- DROP TRIGGER fecha_modificacion_usuario ON compras_tienda.usuario;
 
CREATE TRIGGER fecha_modificacion_usuario
    BEFORE UPDATE 
    ON compras_tienda.usuario
    FOR EACH ROW
    EXECUTE PROCEDURE compras_tienda.actualiza_fecha_modificacion();

COMMENT ON TRIGGER fecha_modificacion_usuario ON compras_tienda.usuario
    IS 'Actualizar fecha modificación tabla Usuario';
	





-- Table: compras_tienda.roles

-- DROP TABLE compras_tienda.roles; 

CREATE TABLE compras_tienda.roles (
                id bigserial NOT NULL,
                descripcion VARCHAR(255),                
                nombre VARCHAR(255),
                activo boolean DEFAULT true,               
				creado_por BIGINT,
                creado_en timestamp without time zone DEFAULT now(),
                modificado_por BIGINT,
				modificado_en timestamp without time zone DEFAULT now(),
                CONSTRAINT roles_pk PRIMARY KEY (id)
				
)
WITH (
    OIDS = FALSE
);

ALTER TABLE compras_tienda.roles
    OWNER to adminstore;

COMMENT ON TABLE compras_tienda.roles
    IS 'Tabla de Roles';
	
-- Trigger: fecha_modificacion_rol

-- DROP TRIGGER fecha_modificacion_rol ON compras_tienda.roles;
 
CREATE TRIGGER fecha_modificacion_rol
    BEFORE UPDATE 
    ON compras_tienda.roles
    FOR EACH ROW
    EXECUTE PROCEDURE compras_tienda.actualiza_fecha_modificacion();

COMMENT ON TRIGGER fecha_modificacion_rol ON compras_tienda.roles
    IS 'Actualizar fecha modificación tabla Roles';
	


 
-- Table: compras_tienda.usuario_role

-- DROP TABLE compras_tienda.usuario_role; 

CREATE TABLE compras_tienda.usuario_role (
                role_id bigserial NOT NULL,
                usuario_id BIGINT NOT NULL,
				activo boolean DEFAULT true,
                creado_por BIGINT,
                creado_en timestamp without time zone DEFAULT now(),
                modificado_por BIGINT,
				modificado_en timestamp without time zone DEFAULT now(),
                CONSTRAINT usuario_role_pk PRIMARY KEY (role_id, usuario_id),
				FOREIGN KEY (role_id) REFERENCES compras_tienda.roles (id) MATCH SIMPLE
					ON DELETE NO ACTION
					ON UPDATE NO ACTION
					NOT DEFERRABLE,
				FOREIGN KEY (usuario_id) REFERENCES compras_tienda.usuario (id) MATCH SIMPLE
					ON DELETE NO ACTION
					ON UPDATE NO ACTION
					NOT DEFERRABLE
)
WITH (
    OIDS = FALSE
);

ALTER TABLE compras_tienda.usuario_role
    OWNER to adminstore;

COMMENT ON TABLE compras_tienda.usuario_role
    IS 'Tabla de Roles Usuarios';
	
-- Trigger: fecha_modificacion_usuario_role

-- DROP TRIGGER fecha_modificacion_usuario_role ON compras_tienda.usuario_role;
 
CREATE TRIGGER fecha_modificacion_usuario_role
    BEFORE UPDATE 
    ON compras_tienda.usuario_role
    FOR EACH ROW
    EXECUTE PROCEDURE compras_tienda.actualiza_fecha_modificacion();

COMMENT ON TRIGGER fecha_modificacion_usuario_role ON compras_tienda.usuario_role
    IS 'Actualizar fecha modificación tabla Roles Usuarios';
	
 
 
-- Table: compras_tienda.sesion

-- DROP TABLE compras_tienda.sesion; 

CREATE TABLE compras_tienda.sesion (
                id bigserial NOT NULL,
				id_usuario BIGINT NOT NULL,
                creado_en timestamp without time zone DEFAULT now(),
                estado BIGINT DEFAULT 1 NOT NULL,
                ingreso_en timestamp without time zone NOT NULL DEFAULT now(),
                num_intentos BIGINT,
				datos_sesion character varying(250),
				sesion_navegador character varying(250),
                token VARCHAR(255),                
                CONSTRAINT sesion_pk PRIMARY KEY (id),
				FOREIGN KEY (id_usuario) REFERENCES compras_tienda.usuario (id) MATCH SIMPLE
					ON DELETE NO ACTION
					ON UPDATE NO ACTION
					NOT DEFERRABLE
)
WITH (
    OIDS = FALSE
);

ALTER TABLE compras_tienda.sesion
    OWNER to adminstore;

COMMENT ON TABLE compras_tienda.sesion
    IS 'Tabla de Sesiones Usuario';
	
-- Trigger: fecha_modificacion_sesion

-- DROP TRIGGER fecha_modificacion_sesion ON compras_tienda.sesion;
 
CREATE TRIGGER fecha_modificacion_sesion
    BEFORE UPDATE 
    ON compras_tienda.sesion
    FOR EACH ROW
    EXECUTE PROCEDURE compras_tienda.actualiza_fecha_modificacion();

COMMENT ON TRIGGER fecha_modificacion_sesion ON compras_tienda.sesion
    IS 'Actualizar fecha modificación tabla Sesiones Usuario';

-- Index: id_sesion_usuario_idx

-- DROP INDEX compras_tienda.id_sesion_usuario_idx;

CREATE INDEX id_sesion_usuario_idx
 ON compras_tienda.sesion USING BTREE
 ( id_usuario ASC );
 
 
 
 
 
-- Table: compras_tienda.mensajes_error

-- DROP TABLE compras_tienda.mensajes_error; 

CREATE TABLE compras_tienda.mensajes_error (
			codigo VARCHAR(6) NOT NULL,
			mensaje VARCHAR(500) NOT NULL,
			activo boolean DEFAULT true,
			generico boolean DEFAULT true,
			id_compania BIGINT,
			creado_por BIGINT,
			creado_en timestamp without time zone DEFAULT now(),
			modificado_por BIGINT,
			modificado_en timestamp without time zone DEFAULT now(),
			CONSTRAINT mensajes_error_pk PRIMARY KEY (codigo)
			
)
WITH (
    OIDS = FALSE
);

ALTER TABLE compras_tienda.mensajes_error
    OWNER to adminstore;

COMMENT ON TABLE compras_tienda.mensajes_error
    IS 'Tabla de Mensajes de Error';
	
-- Trigger: fecha_modificacion_mensajes_error

-- DROP TRIGGER fecha_modificacion_mensajes_error ON compras_tienda.mensajes_error;
 
CREATE TRIGGER fecha_modificacion_mensajes_error
    BEFORE UPDATE 
    ON compras_tienda.mensajes_error
    FOR EACH ROW
    EXECUTE PROCEDURE compras_tienda.actualiza_fecha_modificacion();

COMMENT ON TRIGGER fecha_modificacion_mensajes_error ON compras_tienda.mensajes_error
    IS 'Actualizar fecha modificación tabla Mensajes de Error';
	

	
	
-- Table: compras_tienda.tienda

-- DROP TABLE compras_tienda.tienda; 

CREATE TABLE compras_tienda.tienda (
			id bigserial NOT NULL,
			nombre VARCHAR(200) NOT NULL,
			direccion VARCHAR(500) NOT NULL,
			horario VARCHAR(200) NOT NULL,
			activo boolean DEFAULT true,
			creado_por BIGINT,
			creado_en timestamp without time zone DEFAULT now(),
			modificado_por BIGINT,
			modificado_en timestamp without time zone DEFAULT now(),
			CONSTRAINT tienda_pk PRIMARY KEY (id)
			
)
WITH (
    OIDS = FALSE
);

ALTER TABLE compras_tienda.tienda
    OWNER to adminstore;

COMMENT ON TABLE compras_tienda.tienda
    IS 'Tabla de Tiendas';
	
-- Trigger: fecha_modificacion_tienda

-- DROP TRIGGER fecha_modificacion_tienda ON compras_tienda.tienda;
 
CREATE TRIGGER fecha_modificacion_tienda
    BEFORE UPDATE 
    ON compras_tienda.tienda
    FOR EACH ROW
    EXECUTE PROCEDURE compras_tienda.actualiza_fecha_modificacion();

COMMENT ON TRIGGER fecha_modificacion_tienda ON compras_tienda.tienda
    IS 'Actualizar fecha modificación tabla tienda';
	


	
-- Table: compras_tienda.producto

-- DROP TABLE compras_tienda.producto; 

CREATE TABLE compras_tienda.producto (
			id bigserial NOT NULL,
			nombre VARCHAR(200) NOT NULL,
			descripcion VARCHAR(500),
			codigo_barras VARCHAR(100) NOT NULL,
			precio double precision DEFAULT 0,			
			activo boolean DEFAULT true,
			creado_por BIGINT,
			creado_en timestamp without time zone DEFAULT now(),
			modificado_por BIGINT,
			modificado_en timestamp without time zone DEFAULT now(),
			CONSTRAINT producto_pk PRIMARY KEY (id)
			
)
WITH (
    OIDS = FALSE
);

ALTER TABLE compras_tienda.producto
    OWNER to adminstore;

COMMENT ON TABLE compras_tienda.producto
    IS 'Tabla de Producto';
	
-- Trigger: fecha_modificacion_producto

-- DROP TRIGGER fecha_modificacion_producto ON compras_tienda.producto;
 
CREATE TRIGGER fecha_modificacion_producto
    BEFORE UPDATE 
    ON compras_tienda.producto
    FOR EACH ROW
    EXECUTE PROCEDURE compras_tienda.actualiza_fecha_modificacion();

COMMENT ON TRIGGER fecha_modificacion_producto ON compras_tienda.producto
    IS 'Actualizar fecha modificación tabla producto';
	


	
	
-- Table: compras_tienda.tienda_producto

-- DROP TABLE compras_tienda.tienda_producto; 

CREATE TABLE compras_tienda.tienda_producto (
                tienda_id bigserial NOT NULL,
                producto_id BIGINT NOT NULL,
				cantidad BIGINT DEFAULT 0,
				activo boolean DEFAULT true,
                creado_por BIGINT,
                creado_en timestamp without time zone DEFAULT now(),
                modificado_por BIGINT,
				modificado_en timestamp without time zone DEFAULT now(),
                CONSTRAINT tienda_producto_pk PRIMARY KEY (tienda_id, producto_id),
				FOREIGN KEY (tienda_id) REFERENCES compras_tienda.tienda (id) MATCH SIMPLE
					ON DELETE NO ACTION
					ON UPDATE NO ACTION
					NOT DEFERRABLE,
				FOREIGN KEY (producto_id) REFERENCES compras_tienda.producto (id) MATCH SIMPLE
					ON DELETE NO ACTION
					ON UPDATE NO ACTION
					NOT DEFERRABLE
)
WITH (
    OIDS = FALSE
);

ALTER TABLE compras_tienda.tienda_producto
    OWNER to adminstore;

COMMENT ON TABLE compras_tienda.tienda_producto
    IS 'Tabla de Tienda Producto';
	
-- Trigger: fecha_modificacion_tienda_producto

-- DROP TRIGGER fecha_modificacion_tienda_producto ON compras_tienda.tienda_producto;
 
CREATE TRIGGER fecha_modificacion_tienda_producto
    BEFORE UPDATE 
    ON compras_tienda.tienda_producto
    FOR EACH ROW
    EXECUTE PROCEDURE compras_tienda.actualiza_fecha_modificacion();

COMMENT ON TRIGGER fecha_modificacion_tienda_producto ON compras_tienda.tienda_producto
    IS 'Actualizar fecha modificación tabla Tienda Producto';
	

	
-- Table: compras_tienda.compra

-- DROP TABLE compras_tienda.compra; 

CREATE TABLE compras_tienda.compra (
			id bigserial NOT NULL,
			id_cliente BIGINT NOT NULL,
			id_tienda BIGINT NOT NULL,
			cantidad BIGINT NOT NULL,
			total BIGINT NOT NULL,
			tipo_pago BIGINT NOT NULL,
			observacion VARCHAR(500) NOT NULL,
			estado VARCHAR(20) NOT NULL,
			direccion_envio VARCHAR(500) NOT NULL,	
			fecha_solicitud timestamp without time zone DEFAULT now(),	
			fecha_maxima_entrega timestamp without time zone DEFAULT now(),			
			activo boolean DEFAULT true,
			creado_por BIGINT,
			creado_en timestamp without time zone DEFAULT now(),
			modificado_por BIGINT,
			modificado_en timestamp without time zone DEFAULT now(),
			CONSTRAINT compra_pk PRIMARY KEY (id),
			
			CHECK (estado = 'INICIADO' OR estado = 'ENVIADO' OR estado = 'APROBADO' OR estado = 'RECHAZADO' OR estado = 'PAGADO' OR estado = 'CERRADO'),
			
)
WITH (
    OIDS = FALSE
);

ALTER TABLE compras_tienda.compra
    OWNER to adminstore;

COMMENT ON TABLE compras_tienda.compra
    IS 'Tabla de Compras';
	
-- Trigger: fecha_modificacion_compra

-- DROP TRIGGER fecha_modificacion_compra ON compras_tienda.compra;
 
CREATE TRIGGER fecha_modificacion_compra
    BEFORE UPDATE 
    ON compras_tienda.compra
    FOR EACH ROW
    EXECUTE PROCEDURE compras_tienda.actualiza_fecha_modificacion();

COMMENT ON TRIGGER fecha_modificacion_compra ON compras_tienda.compra
    IS 'Actualizar fecha modificación tabla compra';
	
	
	
	
-- Table: compras_tienda.compra_producto

-- DROP TABLE compras_tienda.compra_producto; 

CREATE TABLE compras_tienda.compra_producto (
                compra_id bigserial NOT NULL,
                producto_id BIGINT NOT NULL,
				activo boolean DEFAULT true,
                creado_por BIGINT,
                creado_en timestamp without time zone DEFAULT now(),
                modificado_por BIGINT,
				modificado_en timestamp without time zone DEFAULT now(),
                CONSTRAINT compra_producto_pk PRIMARY KEY (compra_id, producto_id),
				FOREIGN KEY (compra_id) REFERENCES compras_tienda.compra (id) MATCH SIMPLE
					ON DELETE NO ACTION
					ON UPDATE NO ACTION
					NOT DEFERRABLE,
				FOREIGN KEY (producto_id) REFERENCES compras_tienda.producto (id) MATCH SIMPLE
					ON DELETE NO ACTION
					ON UPDATE NO ACTION
					NOT DEFERRABLE
)
WITH (
    OIDS = FALSE
);

ALTER TABLE compras_tienda.compra_producto
    OWNER to adminstore;

COMMENT ON TABLE compras_tienda.compra_producto
    IS 'Tabla de Compra Producto';
	
-- Trigger: fecha_modificacion_compra_producto

-- DROP TRIGGER fecha_modificacion_compra_producto ON compras_tienda.compra_producto;
 
CREATE TRIGGER fecha_modificacion_compra_producto
    BEFORE UPDATE 
    ON compras_tienda.compra_producto
    FOR EACH ROW
    EXECUTE PROCEDURE compras_tienda.actualiza_fecha_modificacion();

COMMENT ON TRIGGER fecha_modificacion_compra_producto ON compras_tienda.compra_producto
    IS 'Actualizar fecha modificación tabla Compra Producto';
 
				