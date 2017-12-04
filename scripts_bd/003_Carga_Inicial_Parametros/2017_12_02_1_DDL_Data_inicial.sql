	DO $$
	DECLARE iden integer;
	DECLARE secondid integer;
	BEGIN
		
		
		
		
		-- Creacion de rol administrador
		
		
		INSERT INTO compras_tienda.roles(
		descripcion, nombre, creado_por,  modificado_por)
		VALUES ('Administrador Sistema', 'Administrador', 1, 1);
		
		INSERT INTO compras_tienda.roles(
		descripcion, nombre, creado_por,  modificado_por)
		VALUES ('Cliente', 'Cliente', 1, 1);
		
		
		--	'CC', 'Cédula de ciudadanía'   -> 1
		--	'CE', 'Cédula de extranjería'  -> 2
		--	'PA', 'Pasaporte', 'Pasaporte' -> 3
		
		-- Default PassWord   -> 123456
			
		-- Creación de persona Administrador
	  
		INSERT INTO compras_tienda.usuario(nombres, primer_apellido, email, username, birthday, password, numero_documento, id_tipo_documento, creado_por, modificado_por)
		VALUES ('Administrador', 'General', 'admin@gmail.com', 'admin', '1985-01-01','$2a$10$uUKGDftr6zwqwZ2Y/JtlH.4tvZB.3keqxoKHsyOoX4wYxxXs3XQg6', '2131231231', 1, 1, 1);
		
		
		--Asignar ROL de Administrador a Usuario de Login
		
		iden := (SELECT id FROM compras_tienda.roles
				WHERE nombre='Administrador');
				
				
		secondid := (SELECT id FROM compras_tienda.usuario
				WHERE username='admin');
		
		INSERT INTO compras_tienda.usuario_role(role_id, usuario_id,  creado_por,  modificado_por)
		VALUES (iden, secondid, 1, 1);
		
		
		
		-- Creación de persona Cliente	
		
		INSERT INTO compras_tienda.usuario(nombres, primer_apellido, email, username, password, numero_documento, id_tipo_documento, creado_por, modificado_por)
		VALUES ('Pepito', 'Perez', 'clienteuno@gmail.com', 'pepito', '$2a$10$uUKGDftr6zwqwZ2Y/JtlH.4tvZB.3keqxoKHsyOoX4wYxxXs3XQg6', '0', 1, 1, 1);
		
		
		--Asignar ROL de Cliente a Usuario de Login
		
		iden := (SELECT id FROM compras_tienda.roles
				WHERE nombre='Cliente');
				
				
		secondid := (SELECT id FROM compras_tienda.usuario
				WHERE username='pepito');
		
		INSERT INTO compras_tienda.usuario_role(role_id, usuario_id,  creado_por,  modificado_por)
		VALUES (iden, secondid, 1, 1);


	END$$;