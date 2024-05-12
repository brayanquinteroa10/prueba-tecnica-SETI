# Prueba técnica.
Este proyecto es una API creada con Spring Boot y Java. Se utiliza Maven como herramienta de construcción y está diseñada para ser corrida por medio de Docker.

## Servicios

La API expone los siguientes servicios:

### Servicio 1

- **URL:** http://localhost:8080/convert
- **Método:** `POST`
- **Descripción:** `Dado un JSON como Request, este responde su versión correspondiente en XML. De igual forma, si en el Request llega un XML, este la convierte a su correspondiente en JSON.`

### Servicio 2

- **URL:** http://localhost:8080/convertResponse
- **Método:** `POST`
- **Descripción:** `Este endpoint acepta tanto XML como JSON como entrada. Dado un JSON, este da un Response en el formato en el que se respondería en SOAP. Si recibe un XML, la respuesta será en el formato en el que respondería en REST con JSON.`

## Cómo ejecutar el proyecto

1. Construir la imagen Docker: Puedes construir la imagen Docker utilizando el siguiente comando:

```bash
docker build -t prueba-tecnica:0.0.1-SNAPSHOT .
```

2. Ejecutar la imagen Docker. Para ejecutar la imagen Docker, utiliza el siguiente comando:

```bash
docker run -p 8080:8080 prueba-tecnica:0.0.1-SNAPSHOT
```

Ahora, la API debería estar ejecutándose en http://localhost:8080

