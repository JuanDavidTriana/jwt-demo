# Spring Boot JWT Demo

Este es un proyecto básico de Spring Boot que demuestra el uso de JWT (JSON Web Tokens) para autenticación con conexión a base de datos MySQL.

## Características

- ✅ Autenticación con JWT
- ✅ Registro de usuarios
- ✅ Login de usuarios
- ✅ Endpoints protegidos y públicos
- ✅ Conexión a MySQL
- ✅ Encriptación de contraseñas con BCrypt
- ✅ Validación de datos
- ✅ Manejo de errores

## Requisitos Previos

- Java 17 o superior
- Maven
- PostgreSQL 12 o superior

## Configuración de la Base de Datos

1. Asegúrate de que PostgreSQL esté ejecutándose en tu sistema
2. Crea una base de datos llamada `jwt_demo` o modifica la configuración en `application.properties`
3. Actualiza las credenciales de PostgreSQL en `application.properties`:
   ```properties
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

## Ejecutar el Proyecto

1. Clona o descarga el proyecto
2. Navega al directorio del proyecto
3. Ejecuta el comando:
   ```bash
   mvn spring-boot:run
   ```
4. La aplicación estará disponible en `http://localhost:8080`

## Endpoints Disponibles

### Endpoints Públicos (No requieren autenticación)

#### Registro de Usuario
```http
POST /api/auth/registro
Content-Type: application/json

{
  "username": "usuario1",
  "email": "usuario1@example.com",
  "password": "123456",
  "nombreCompleto": "Usuario Ejemplo"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "usuario1",
  "password": "123456"
}
```

#### Test de API
```http
GET /api/auth/test
```

#### Endpoint Público de Prueba
```http
GET /api/test/public
```

### Endpoints Protegidos (Requieren autenticación)

#### Endpoint Protegido
```http
GET /api/test/protected
Authorization: Bearer <tu_token_jwt>
```

#### Información del Usuario
```http
GET /api/test/user-info
Authorization: Bearer <tu_token_jwt>
```

## Flujo de Uso

1. **Registrar un usuario**: Usa el endpoint `/api/auth/registro` para crear una cuenta
2. **Hacer login**: Usa el endpoint `/api/auth/login` para obtener un token JWT
3. **Usar endpoints protegidos**: Incluye el token en el header `Authorization: Bearer <token>`

## Ejemplo de Uso con cURL

### 1. Registrar un usuario
```bash
curl -X POST http://localhost:8080/api/auth/registro \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "123456",
    "nombreCompleto": "Usuario de Prueba"
  }'
```

### 2. Hacer login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "123456"
  }'
```

### 3. Usar endpoint protegido
```bash
curl -X GET http://localhost:8080/api/test/protected \
  -H "Authorization: Bearer <token_recibido_del_login>"
```

## Estructura del Proyecto

```
src/main/java/com/example/demo/
├── config/
│   └── SecurityConfig.java          # Configuración de Spring Security
├── controller/
│   ├── AuthController.java          # Controlador de autenticación
│   └── TestController.java          # Controlador de pruebas
├── dto/
│   ├── AuthResponse.java            # DTO para respuesta de autenticación
│   ├── LoginRequest.java            # DTO para petición de login
│   └── RegistroRequest.java        # DTO para petición de registro
├── model/
│   └── Usuario.java                # Entidad Usuario
├── repository/
│   └── UsuarioRepository.java      # Repositorio JPA
├── security/
│   └── JwtAuthenticationFilter.java # Filtro JWT
├── service/
│   ├── AuthService.java            # Servicio de autenticación
│   ├── CustomUserDetailsService.java # Servicio personalizado de UserDetails
│   └── JwtService.java            # Servicio JWT
└── DemoApplication.java            # Clase principal
```

## Configuración JWT

El proyecto incluye configuración JWT en `application.properties`:

```properties
jwt.secret=mySecretKey123456789012345678901234567890123456789012345678901234567890
jwt.expiration=86400000
```

- `jwt.secret`: Clave secreta para firmar los tokens (en producción, usa una clave más segura)
- `jwt.expiration`: Tiempo de expiración del token en milisegundos (24 horas por defecto)

## Configuración PostgreSQL

El proyecto está configurado para usar PostgreSQL. Asegúrate de:

1. **Instalar PostgreSQL** en tu sistema
2. **Crear la base de datos**:
   ```sql
   CREATE DATABASE jwt_demo;
   ```
3. **Actualizar credenciales** en `application.properties` si es necesario
4. **Ejecutar el script** `database_init_postgresql.sql` para datos de prueba

## Seguridad

- Las contraseñas se encriptan con BCrypt
- Los tokens JWT tienen tiempo de expiración
- Validación de datos en los DTOs
- Manejo de errores en los controladores
- Configuración CORS para desarrollo

## Notas Importantes

- Este es un proyecto de demostración, no usar en producción sin mejoras de seguridad
- En producción, cambia la clave secreta JWT por una más segura
- Considera agregar refresh tokens para mejor seguridad
- Implementa logging apropiado para producción
- Agrega tests unitarios y de integración 