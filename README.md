COMPRAS API TEST: 
================

servicios web REST base para compras basado en SPRING. Usa PostgreSQL como base de datos.

**Requerimientos** : Java 8.0, 
		     PostgresSQL, 
		     Gradle, 
		     Eclipse.


###  Clonar / Descargar

Clonar el repositorio usando Git:

```bash
git clone https://github.com/davince8502/compras_api.git
```

De otra forma, puede [decargar](https://github.com/davince8502/compras_api/archive/master.zip)
el repositorio en .zip.

###  Importar Proyecto

Importar el proyecto Gradle en eclipse, se puede seguir la [guia](https://docs.spring.io/sts/docs/2.9.0.old/reference/html/gradle/gradle-sts-tutorial.html).


###  Configurar Base de Datos

Ejecutar los scripts `sql` que se encuentran en la carpeta `scripts_bd`.

* **/001_Crear_DB_y_Esquemas/**

Crear base de datos 

```shell
Ejecutar 2017_12_02_1_DDL_Crear_DB.sql
```

Crear Usuario con permisos

```shell
Ejecutar 2017_12_02_2_DDL_Crear_Usuario.sql
```

 Crear Esquema

```shell
Ejecutar 2017_12_02_3_DDL_Crear_Esquemas.sql
```

* **/002_Crear_tablas/**

Crear Tablas

```shell
Ejecutar 2017_03_12_1_DDL_Crear_Tablas.sql
```

* **/003_Carga_Inicial_Parametros/**

Cargar datos iniciales

```shell
Ejecutar 2017_12_02_1_DDL_Data_inicial.sql
```

###  Desplegar Servidor

Compilar el proyecto y desplegar el WAR en Tomcat >= v8.0  ó Wildfly >= 9.0.

Debera ver el siguiente resultado:

```shell
 .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.9.RELEASE)

2017-12-04 23:09:00.730  INFO 1124 --- [ost-startStop-1] com.compras.ServletInitializer           : Starting ServletInitializer on hogar with PID 1124 (started by MIGUELLEONARDO in D:\Java\spring-tool-suite-3.7.3.RELEASE-e4.6-win32-x86_64\sts-bundle\sts-3.7.3.RELEASE)
2017-12-04 23:09:00.734  INFO 1124 --- [ost-startStop-1] com.compras.ServletInitializer           : No active profile set, falling back to default profiles: default
2017-12-04 23:09:00.925  INFO 1124 --- [ost-startStop-1] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@1716654b: startup date [Mon Dec 04 23:09:00 COT 2017]; root of context hierarchy
2017-12-04 23:09:01.477  INFO 1124 --- [ost-startStop-1] o.s.c.a.ConfigurationClassParser         : Properties location [classpath:properties/aplicacion.properties] not resolvable: class path resource [properties/aplicacion.properties] cannot be opened because it does not exist
2017-12-04 23:09:01.500  INFO 1124 --- [ost-startStop-1] o.s.c.a.ConfigurationClassParser         : Properties location [classpath:config-project.properties] not resolvable: class path resource [config-project.properties] cannot be opened because it does not exist
2017-12-04 23:09:02.658  INFO 1124 --- [ost-startStop-1] o.s.b.f.s.DefaultListableBeanFactory     : Overriding bean definition for bean 'jpaAuditingHandler' with a different definition: replacing [Root bean: class [org.springframework.data.auditing.AuditingHandler]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null] with [Root bean: class [org.springframework.data.auditing.AuditingHandler]; scope=; abstract=false; lazyInit=false; autowireMode=2; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null]
2017-12-04 23:09:04.887  INFO 1124 --- [ost-startStop-1] o.a.c.c.C.[.[localhost].[/compras_api]   : Initializing Spring embedded WebApplicationContext
2017-12-04 23:09:04.887  INFO 1124 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 3963 ms
2017-12-04 23:09:06.319  INFO 1124 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'simpleCorsFilter' to: [/*]
2017-12-04 23:09:06.319  INFO 1124 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'errorPageFilter' to: [/*]
2017-12-04 23:09:06.319  INFO 1124 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-12-04 23:09:06.320  INFO 1124 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2017-12-04 23:09:06.320  INFO 1124 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2017-12-04 23:09:06.320  INFO 1124 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2017-12-04 23:09:06.322  INFO 1124 --- [ost-startStop-1] .s.DelegatingFilterProxyRegistrationBean : Mapping filter: 'springSecurityFilterChain' to: [/*]
2017-12-04 23:09:06.323  INFO 1124 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-12-04 23:09:09.865  INFO 1124 --- [ost-startStop-1] j.LocalContainerEntityManagerFactoryBean : Building JPA container EntityManagerFactory for persistence unit 'default'
2017-12-04 23:09:09.902  INFO 1124 --- [ost-startStop-1] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [
	name: default
	...]
2017-12-04 23:09:10.093  INFO 1124 --- [ost-startStop-1] org.hibernate.Version                    : HHH000412: Hibernate Core {5.0.12.Final}
2017-12-04 23:09:10.097  INFO 1124 --- [ost-startStop-1] org.hibernate.cfg.Environment            : HHH000206: hibernate.properties not found
2017-12-04 23:09:10.099  INFO 1124 --- [ost-startStop-1] org.hibernate.cfg.Environment            : HHH000021: Bytecode provider name : javassist
2017-12-04 23:09:10.213  INFO 1124 --- [ost-startStop-1] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.0.1.Final}
2017-12-04 23:09:10.533  INFO 1124 --- [ost-startStop-1] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.PostgreSQL94Dialect
2017-12-04 23:09:10.779  INFO 1124 --- [ost-startStop-1] o.h.e.j.e.i.LobCreatorBuilderImpl        : HHH000424: Disabling contextual LOB creation as createClob() method threw error : java.lang.reflect.InvocationTargetException
2017-12-04 23:09:10.784  INFO 1124 --- [ost-startStop-1] org.hibernate.type.BasicTypeRegistry     : HHH000270: Type registration [java.util.UUID] overrides previous : org.hibernate.type.UUIDBinaryType@64565ab7
2017-12-04 23:09:12.339  INFO 1124 --- [ost-startStop-1] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
2017-12-04 23:09:12.684  INFO 1124 --- [ost-startStop-1] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2017-12-04 23:09:16.674  INFO 1124 --- [ost-startStop-1] o.s.s.web.DefaultSecurityFilterChain     : Creating filter chain: org.springframework.security.web.util.matcher.AnyRequestMatcher@1, [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@69028426, org.springframework.security.web.context.SecurityContextPersistenceFilter@5be4fd4d, org.springframework.security.web.header.HeaderWriterFilter@35389569, org.springframework.security.web.authentication.logout.LogoutFilter@256844ef, com.tienda.security.auth.jwt.JwtTokenAuthenticationProcessingFilter@244415, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@55825541, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@3da72395, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@71fef5f2, org.springframework.security.web.session.SessionManagementFilter@a69312b, org.springframework.security.web.access.ExceptionTranslationFilter@1ee526ba, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@4a7551b1]
2017-12-04 23:09:17.629  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@1716654b: startup date [Mon Dec 04 23:09:00 COT 2017]; root of context hierarchy
2017-12-04 23:09:17.869  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/saveCompra],methods=[POST],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.CompraController.saveCompra(com.compras.domain.model.Compra)
2017-12-04 23:09:17.874  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/login],methods=[POST],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.LoginController.loginUser(com.compras.domain.model.Usuario)
2017-12-04 23:09:17.876  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/saveProducto],methods=[POST],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.PruductoController.saveProducto(com.compras.domain.model.Producto)
2017-12-04 23:09:17.877  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/updateProducto],methods=[PUT],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.PruductoController.updateProducto(com.compras.domain.model.Producto)
2017-12-04 23:09:17.878  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/getProducto],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.PruductoController.getProducto(java.lang.Long)
2017-12-04 23:09:17.878  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/deleteProducto],methods=[DELETE],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.PruductoController.deleteProducto(com.compras.domain.model.Producto)
2017-12-04 23:09:17.880  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/saveTienda],methods=[POST],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.TiendaController.saveProducto(com.compras.domain.model.Tienda)
2017-12-04 23:09:17.881  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/updateTienda],methods=[PUT],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.TiendaController.updateProducto(com.compras.domain.model.Tienda)
2017-12-04 23:09:17.881  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/getTienda],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.TiendaController.getTienda(java.lang.Long)
2017-12-04 23:09:17.882  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/deleteTienda],methods=[DELETE],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.TiendaController.deleteProducto(com.compras.domain.model.Tienda)
2017-12-04 23:09:17.882  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/addProductosToTienda],methods=[POST],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.TiendaController.addProductosToTienda(com.compras.domain.model.Tienda)
2017-12-04 23:09:17.884  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/getUsuario],methods=[GET],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.UsuarioController.getUsuario(java.lang.String)
2017-12-04 23:09:17.885  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/saveUsuario],methods=[POST],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.UsuarioController.saveUsuario(com.compras.domain.model.Usuario)
2017-12-04 23:09:17.886  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/updateUsuario],methods=[PUT],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.UsuarioController.updateUsuario(com.compras.domain.model.Usuario)
2017-12-04 23:09:17.887  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/auth/deleteUsuario],methods=[DELETE],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<com.compras.domain.dto.ResponseServiceDTO> com.compras.controller.UsuarioController.deleteUsuario(com.compras.domain.model.Usuario)
2017-12-04 23:09:17.899  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-12-04 23:09:17.899  INFO 1124 --- [ost-startStop-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-12-04 23:09:18.011  INFO 1124 --- [ost-startStop-1] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-12-04 23:09:18.012  INFO 1124 --- [ost-startStop-1] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-12-04 23:09:18.179  INFO 1124 --- [ost-startStop-1] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-12-04 23:09:19.416  INFO 1124 --- [ost-startStop-1] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-12-04 23:09:19.454  INFO 1124 --- [ost-startStop-1] com.compras.ServletInitializer           : Started ServletInitializer in 20.415 seconds (JVM running for 27.526)
2017-12-04 23:09:19.505  INFO 1124 --- [           main] org.apache.coyote.ajp.AjpNioProtocol     : Starting ProtocolHandler ["ajp-nio-8009"]
2017-12-04 23:09:19.510  INFO 1124 --- [           main] org.apache.catalina.startup.Catalina     : Server startup in 26223 ms
2017-12-04 23:09:23.323  INFO 1124 --- [nio-8080-exec-3] o.a.c.c.C.[.[localhost].[/compras_api]   : Initializing Spring FrameworkServlet 'dispatcherServlet'
2017-12-04 23:09:23.324  INFO 1124 --- [nio-8080-exec-3] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2017-12-04 23:09:23.367  INFO 1124 --- [nio-8080-exec-3] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 43 ms
```

Se pueden observar los diferentes ENDPOINTS REST configurados.

### Revisar Configuracion Proyecto

Abrir el archivo `/compras_api_rest/src/main/resources/application.yml` para revisar las propiedades de conexión y seguridad.

Datos de Conexión PostgresSQL.

```shell
spring.datasource:
  url: jdbc:postgresql://localhost:5432/compras_tienda?currentSchema=compras_tienda
  driverClassName: org.postgresql.Driver
  username: adminstore
  password: tienda1234
  data: "classpath*:data.sql"
  
spring.jpa:
  database-platform: org.hibernate.dialect.PostgreSQL94Dialect

```


Datos de Generación JWT - JSON Web Token

```shell
rest.security.jwt:
  tokenExpirationTime: 60 # Number of minutes
  refreshTokenExpTime: 60 # Minutes
  tokenIssuer: compras_api
  tokenSigningKey: pass12345
  
rest.security.web:
  pathWihtoutAuth: /api/**  
  tokenBasedAuthEntryPoint: /auth/**

```
Si desea cambiar el tiempo de vida del Token para los servicios REST puede modificar el parametro `tokenExpirationTime`.






Probar Servicios REST
---------------------------


Mediante un cliente de servicios como [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop), importar los siguientes comandos de linea CURL:


### Login / Generacion Token


```shell

curl -X POST \
  http://localhost:8080/compras_api/auth/login \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"password":"123456",
	"email":"admin@gmail.com"
}'
```

Resultado despues de loguearse satisfactoriamente:

```shell
{
    "responseCode": "000000",
    "responseMessage": null,
    "data": {
        "accessToken": {
            "expireTime": "1512450091000",
            "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6WyJBZG1pbmlzdHJhZG9yIl0sImRhdGFVc2VyIjoxLCJpc3MiOiJjb21wcmFzX2FwaSIsImlhdCI6MTUxMjQ0NjQ5MSwiZXhwIjoxNTEyNDUwMDkxfQ.bT65kmzliSyJgsA8lcuLPjjouGdHDq3mJmwfYumLCKrN8MG3T5dw0JfgOTMZTQGZaFYz1d7q_jTnAHMSOjUt6g"
        },
        "user": {
            "creadoEn": 1512442039515,
            "creadoPor": 1,
            "modificadoEn": 1512442039515,
            "modificadoPor": 1,
            "id": 1,
            "birthday": 473403600000,
            "cambioClave": false,
            "email": "admin@gmail.com",
            "estado": 1,
            "fullname": null,
            "genero": 1,
            "idTipoDocumento": 1,
            "nombres": "Administrador",
            "numeroDocumento": "2131231231",
            "password": null,
            "primerApellido": "General",
            "segundoApellido": null,
            "telefono": null,
            "username": "admin"
        }
    },
    "errores": null
}
```

Se obtiene un `responseCode` igual `000000` lo que indica una respuesta exitosa. El `accessToken` contiene los datos del Token a ser usado en los demas servicios REST mientras su tiempo de vida este vigente.

Los Datos del cliente (Usuario) se ven en el objeto `user`.



### Crear Producto


```shell
curl -X POST \
  http://localhost:8080/compras_api/auth/saveProducto \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6WyJBZG1pbmlzdHJhZG9yIl0sImRhdGFVc2VyIjoxLCJpc3MiOiJjb21wcmFzX2FwaSIsImlhdCI6MTUxMjQ0NjQ5MSwiZXhwIjoxNTEyNDUwMDkxfQ.bT65kmzliSyJgsA8lcuLPjjouGdHDq3mJmwfYumLCKrN8MG3T5dw0JfgOTMZTQGZaFYz1d7q_jTnAHMSOjUt6g' \
  -d '{
	"nombre":"Bocadillo",
	"descripcion":"Bocadillo de guayaba",
	"codigoBarras":"121212121"
}'
```

Resultado:

```shell
{
    "responseCode": "000000",
    "responseMessage": null,
    "data": null,
    "errores": null
}
```
Se puede observar, que se uso un atributo de cabecera `x-authorization` donde se concatena a la palabra `bearer` y un espacio ` ` el token generado en el servicio de Login.

Si no se usa ese atributo en la cabecera los servicios responderan de la siguiente forma:

```shell
{
    "status": 401,
    "message": "Authentication failed: Authorization header cannot be blank!",
    "errorCode": 1000,
    "timestamp": 1512451392483
}
```

En caso de que el token haya expirado, los servicios responderan:

```shell
{
    "status": 401,
    "message": "Token has expired",
    "errorCode": 1003,
    "timestamp": 1512451406123
}
```

### Obtener Producto

```shell
curl -X GET \
  'http://localhost:8080/compras_api/auth/getProducto?idProducto=1' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6WyJBZG1pbmlzdHJhZG9yIl0sImRhdGFVc2VyIjoxLCJpc3MiOiJjb21wcmFzX2FwaSIsImlhdCI6MTUxMjQ0MjgyOCwiZXhwIjoxNTEyNDQ2NDI4fQ.Y2MxADlTi7MqAEeb7VEIu4YSXMCyyVh8pRUJDCZo6L97jM2lSNb69fQQzWd0UbG3-kh3pXh07HmDTBsel7fnAQ'
```
Resultado:

```shell
{
    "responseCode": "000000",
    "responseMessage": null,
    "data": {
        "creadoEn": 1512443492123,
        "creadoPor": 1,
        "modificadoEn": 1512443492123,
        "modificadoPor": 1,
        "id": 1,
        "activo": null,
        "codigoBarras": "7898777678",
        "descripcion": "galletas de leche",
        "nombre": "Galletas Nucita"
    },
    "errores": null
}
```


### Crear Tienda

```shell
curl -X POST \
  http://localhost:8080/compras_api/auth/saveTienda \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6WyJBZG1pbmlzdHJhZG9yIl0sImRhdGFVc2VyIjoxLCJpc3MiOiJjb21wcmFzX2FwaSIsImlhdCI6MTUxMjQ0MjgyOCwiZXhwIjoxNTEyNDQ2NDI4fQ.Y2MxADlTi7MqAEeb7VEIu4YSXMCyyVh8pRUJDCZo6L97jM2lSNb69fQQzWd0UbG3-kh3pXh07HmDTBsel7fnAQ' \
  -d '{
	"nombre":"Tienda SuperMarket",
	"direccion":"Calle 1 # 23-23",
	"horario":"8:00 am - 8:00 pm"
}'

```



### Adicionar Productos Tienda

```shell
curl -X POST \
  http://localhost:8080/compras_api/auth/addProductosToTienda \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6WyJBZG1pbmlzdHJhZG9yIl0sImRhdGFVc2VyIjoxLCJpc3MiOiJjb21wcmFzX2FwaSIsImlhdCI6MTUxMjQ0MjgyOCwiZXhwIjoxNTEyNDQ2NDI4fQ.Y2MxADlTi7MqAEeb7VEIu4YSXMCyyVh8pRUJDCZo6L97jM2lSNb69fQQzWd0UbG3-kh3pXh07HmDTBsel7fnAQ' \
  -d '{
	"id":"1",
	"productos":[
		{
			"id":"1",
			"cantidad":"23",
			"precio":"1200"
		},
		{
			"id":"5",
			"cantidad":"15",
			"precio":"800"
		}		
	]
}'
```



### Crear Compra

```shell
curl -X POST \
  http://localhost:8080/compras_api/auth/saveCompra \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6WyJBZG1pbmlzdHJhZG9yIl0sImRhdGFVc2VyIjoxLCJpc3MiOiJjb21wcmFzX2FwaSIsImlhdCI6MTUxMjQ0NjQ5MSwiZXhwIjoxNTEyNDUwMDkxfQ.bT65kmzliSyJgsA8lcuLPjjouGdHDq3mJmwfYumLCKrN8MG3T5dw0JfgOTMZTQGZaFYz1d7q_jTnAHMSOjUt6g' \
  -d '{
	"cantidad":"4",
	"direccionEnvio":"calle 234 # 223-32",
	"fechaSolicitud":"2017-12-04",
	"fechaMaximaEntrega":"2017-12-08",
	"idCliente":"1",
	"idTienda":"1",
	"observacion":"observacion xxx",
	"tipoPago":"1",
	"totalCompra":"4000",
	
	"productos":[
		{
			"id":"1",
			"cantidad":"2",
			"precio":"1200"
		},
		{
			"id":"5",
			"cantidad":"2",
			"precio":"800"
		}		
	]
}'
```


Diagrama Modelo Entidades NoSQL
-------------------------------


El siguiente diagrama muestra el arbol de entidades:


```shell
"compra":{
        "creadoEn": 1512446964321,
        "creadoPor": 1,
        "modificadoEn": 1512446964321,
        "modificadoPor": 1,
        "id": 3,
        "activo": true,
        "cantidad": 4,
        "direccionEnvio": "calle 234 # 223-32",
        "fechaMaximaEntrega": 1512691200000,
        "fechaSolicitud": 1512345600000,
        "idCliente": 1,
        "cliente": {
            "creadoEn": 1512442039515,
            "creadoPor": 1,
            "modificadoEn": 1512442039515,
            "modificadoPor": 1,
            "id": 1,
            "birthday": 473403600000,
            "cambioClave": false,
            "email": "admin@gmail.com",
            "estado": 1,
            "fullname": null,
            "genero": 1,
            "idTipoDocumento": 1,
            "nombres": "Administrador",
            "numeroDocumento": "2131231231",
            "password": null,
            "primerApellido": "General",
            "segundoApellido": null,
            "telefono": null,
            "username": "admin"
        },
        "idTienda": 1,
        "tienda": {
            "creadoEn": 1512444493119,
            "creadoPor": 1,
            "modificadoEn": 1512444493119,
            "modificadoPor": 1,
            "id": 1,
            "activo": true,
            "direccion": "Calle 1 # 23-23",
            "horario": "8:00 am - 8:00 pm",
            "nombre": "Tienda SuperMarket",
            "productos": [
					{
						"creadoEn": 1512443492123,
						"creadoPor": 1,
						"modificadoEn": 1512443626774,
						"modificadoPor": 1,
						"id": 1,
						"activo": true,
						"codigoBarras": "7898777678",
						"descripcion": "galletas de leche",
						"nombre": "Galletas Nucita"					   
					},
					{
						"creadoEn": 1512444343540,
						"creadoPor": 1,
						"modificadoEn": 1512444343540,
						"modificadoPor": 1,
						"id": 5,
						"activo": true,
						"codigoBarras": "121212121",
						"descripcion": "galletas de leche",
						"nombre": "Bocadillo"					   
					}
				]
        },
        "observacion": "observacion compra 1",
        "estado": "INICIADO",
        "tipoPago": 1,
        "totalCompra": 4000,
        "productos": [
            {
                "creadoEn": 1512443492123,
                "creadoPor": 1,
                "modificadoEn": 1512443626774,
                "modificadoPor": 1,
                "id": 1,
                "activo": true,
                "codigoBarras": "7898777678",
                "descripcion": "galletas de leche",
                "nombre": "Galletas Nucita"              
            },
            {
                "creadoEn": 1512444343540,
                "creadoPor": 1,
                "modificadoEn": 1512444343540,
                "modificadoPor": 1,
                "id": 5,
                "activo": true,
                "codigoBarras": "121212121",
                "descripcion": "galletas de leche",
                "nombre": "Bocadillo"               
            }
        ]
    }
```



