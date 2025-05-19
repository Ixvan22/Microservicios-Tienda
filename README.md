
# Microservicios-Tienda

Proyecto de microservicios para una tienda online, implementado con Java 21 y Spring Boot. El sistema está diseñado usando una arquitectura basada en eventos con Apache Kafka para la comunicación entre servicios y está dockerizado para facilitar su despliegue y escalabilidad.

---

## Arquitectura y componentes

El proyecto consta de los siguientes microservicios:

- **eureka-server**  
  Servidor Eureka para el registro y descubrimiento dinámico de los microservicios.

- **gateway**  
  Puerta de entrada única (API Gateway) que enruta las peticiones hacia los microservicios correspondientes.

- **productos**  
  Gestiona la base de datos de productos. Permite crear, actualizar y listar productos mediante llamadas REST.  
  - Envía eventos a Kafka cuando cambia el precio de un producto.  
  - Escucha eventos Kafka para reservar stock y confirmar o rechazar reservas.

- **pedidos**  
  Permite crear pedidos vía REST.  
  - Mantiene una copia local actualizada de los precios de productos mediante un listener Kafka, evitando cuellos de botella en llamadas REST.  
  - Publica eventos Kafka cuando se crea un pedido y se reserva el stock.  
  - Escucha eventos para confirmar o rechazar pedidos según el resultado del pago.

- **pagos**  
  Simula el procesamiento de pagos, generando respuestas correctas o erróneas para validar el flujo de confirmación de pedidos.

---

## Tecnologías utilizadas

- Java 21  
- Spring Boot  
- Apache Kafka  
- MySQL  
- Docker (contenedores para todos los microservicios)  
- REST API  

---

## Cómo ejecutar el proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/Ixvan22/Microservicios-Tienda.git
cd Microservicios-Tienda
```

2. Levantar los contenedores Docker (asegúrate de tener Docker instalado y corriendo):

```bash
docker-compose up --build
```

Este comando iniciará todos los microservicios, la base de datos MySQL, Kafka y Zookeeper, y el servidor Eureka.

3. Acceder al gateway para consumir los servicios (puerto configurado en `docker-compose.yml` y aplicación).

---

## Flujo general

- Los productos pueden ser creados, modificados y listados. Cambios en precios son publicados vía Kafka para mantener sincronizados otros servicios.  
- Los pedidos se crean consultando precios locales actualizados por eventos Kafka, reservando stock y esperando confirmación de pago.  
- El servicio de pagos simula las transacciones y envía eventos para confirmar o rechazar pedidos.  
- Eureka facilita el descubrimiento automático de servicios y el gateway centraliza las peticiones.

---

## Notas adicionales

- El sistema está diseñado para evitar cuellos de botella en consultas críticas, usando Kafka para comunicación asincrónica y sincronización eventual.  
- Todos los servicios están containerizados, facilitando su despliegue y escalabilidad en entornos locales o en la nube.
