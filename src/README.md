## 🔹Backend Developer Test - Nunegal Consulting

Este proyecto es una solución para la prueba técnica de backend de **Nunegal Consulting**, desarrollada con **Java 21 (Correto 21)** y **Spring Boot 3.5.0**.
La API permite obtener productos similares a un producto dado, utilizando un mock de API externa y una base de datos InfluxDB para almacenar métricas de rendimiento.
---

## 🔹 Requisitos

- [X] Java 21
- [X] Maven 3.8+ o ./mvnw
- [X] Docker
- [X] Docker Compose


## 🔹 Como podemos Ejecutar la aplicación 

#### 1. Clonar el repositorio

```
git clone https://github.com/<TU_USUARIO>/backendDevTest.git
cd backendDevTest
```

#### 2. Levantar los servicios necesarios con Docker Compose

```
docker-compose up -d simulado influxdb grafana
```

Esto levantará:

* El mock del API en: http://localhost:3001
* Grafana en: http://localhost:3000


#### 3. Ejecutar la API de productos similares
```
./mvnw spring-boot:run
```
Esto iniciará la aplicación Spring Boot en el puerto 5000.
http://localhost:5000
---
### ✅ Endpoint principal
```
GET /product/{productId}/similar

Ejemplo:
GET http://localhost:5000/product/1/similar
```

### ✅ Respuesta:
```
[
    {
        "id": 2,
        "name": "Dress",
        "price": 19.99,
        "avaliability": false
    },
    {
        "id": 3,
        "name": "Blazer",
        "price": 29.99,
        "avaliability": false
    },
    {
        "id": 4,
        "name": "Boots",
        "price": 39.99,
        "avaliability": false
    }
]
``` 
---
### ✅ Test de rendimiento K6
Para poder ejecutar los test de rendimiento K6, primero debes levantar las instancias con Docker.

```
docker-compose run --rm k6 run scripts/test.js
```
Esto ejecutará el script de K6 que simula llamadas concurrentes durante 1,30, realizando peticiones al endpoint `/product/{productId}/similar`.
```
http://localhost:3000/d/Le2Ku9NMk/k6-performance-test
```
### ✅ Estructura del proyecto
```
src/
├── main/
│   ├── java/com/aruimari/springboot/di.app
│   │   ├── client/
│   │   ├── controllers/
│   │   ├── excepetions/
│   │   ├── models/
│   │   └── services/
│   └── resources/
│       ├── application.properties
│       └── config.properties
└── test/
    └── java/com/aruimari/springboot/di/app

```

## ✅ Pendiente

Mejorar cobertura de test unitario y de integración
Manejo más robusto de excepciones HTTP del cliente

Desarrollar un cliente de API más robusto con manejo de errores y reintentos.

---

### Autor de esta prueba técnica: `Antonio Ruiz Marin`