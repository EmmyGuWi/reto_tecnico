# Auth H2 BCrypt

Proyecto de ejemplo para autenticación de usuario y contraseña con:

- Java 21
- Spring Boot 4.0.7
- Maven
- Spring Web
- Spring Data JPA
- Spring Security
- BCrypt
- H2 en memoria
- Bean Validation
- CORS para Angular en `http://localhost:4200`

## Usuario de prueba

- Usuario: `admin`
- Password: `12345678`

El password NO se guarda en texto plano. Al arrancar la aplicación se genera un hash BCrypt y se guarda en H2.

## Ejecutar

Desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

O:

```bash
mvn clean package
java -jar target/auth-h2-bcrypt-0.0.1-SNAPSHOT.jar
```

## Endpoint

POST:

```text
http://localhost:8080/auth/login
```

Body:

```json
{
  "usuario": "admin",
  "password": "12345678"
}
```

Respuesta correcta:

```json
{
  "authenticated": true,
  "message": "Autenticación correcta"
}
```

HTTP 200.

Credenciales incorrectas:

```json
{
  "authenticated": false,
  "message": "Credenciales incorrectas"
}
```

HTTP 401.

## Consola H2

Abrir:

```text
http://localhost:8080/h2-console
```

Datos:

```text
JDBC URL: jdbc:h2:mem:authdb
User Name: sa
Password: [vacío]
```

Consulta:

```sql
SELECT * FROM USUARIOS;
```

Verás el usuario `admin` y el password almacenado como hash BCrypt.

## Angular

La carpeta `frontend-example` contiene:

- `loggin.ts`
- `loggin.html`
- ejemplo de `provideHttpClient()`

Puedes copiar esos archivos/adaptarlos a tu proyecto Angular actual.

## Flujo

```text
Angular
   |
   | POST /auth/login
   | usuario + password plano sobre HTTP local
   v
AuthController
   |
   v
AuthService
   |
   +--> busca usuario en H2
   |
   +--> passwordEncoder.matches(passwordPlano, hashBCrypt)
   |
   +--> true  -> HTTP 200
   |
   +--> false -> HTTP 401
```

Para producción debes usar HTTPS y no registrar passwords en logs.
