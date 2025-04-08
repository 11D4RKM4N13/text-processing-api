# 📘 Text Processing API

Este microservicio desarrollado con **Spring Boot** expone una API REST para procesar entradas de texto. A partir de líneas con un número y una frase separadas por una barra invertida (`\`), evalúa si el número corresponde con el conteo de palabras de la frase.

---

## 🚀 Tecnologías

- Java 17+
- Spring Boot 3
- Maven
- OpenAPI (Contract-First)
- JUnit 5

---

## 📦 Estructura del Proyecto

```plaintext
src/
 └── main/
     ├── java/com/edwin/textoapi
     │   ├── controller
     │   ├── service
     │   ├── exception
     │   └── model
     └── resources/
         ├── application.properties
         └── openapi.yaml
``` 

## Cómo ejecutar el proyecto localmente

1. Construye el proyecto con Maven

Utilice el siguiente comando de bash para compilar el proyecto.

```bash
./mvnw clean install

./mvnw clean compile
```

2. Ejecuta la aplicación

Utilice el siguiente comando de bash para ejecutar el proyecto.

```bash
./mvnw spring-boot:run
```

## Pruebas unitarias

Utilice el siguiente comando de bash para ejecutar las pruebas unitarias
del proyecto.

```bash
./mvnw clean verify
./mvnw test

target/site/jacoco/index.html
```

## Documentación

Utilice la siguiente ruta para ver la documentación del API en Swagger.

```http request
http://localhost:8080/swagger-ui.html

```
