# SkyCompass - Sistema de Gestión Aeroportuaria ✈️[cite: 1]

SkyCompass es una aplicación de software diseñada para gestionar, monitorizar y facilitar las operaciones de un aeropuerto de forma integral[cite: 1]. El sistema permite organizar de manera clara la programación de vuelos, asignación de pistas, terminales, puertas de embarque y aparcamientos[cite: 1]. 

El núcleo de la aplicación facilita la interacción y conexión en tiempo real entre operadores, controladores y el gestor principal mediante un sistema de mensajes y notificaciones[cite: 1].

## 🛠️ Arquitectura y Tecnologías

*   **Lenguaje Principal:** Java[cite: 1].
*   **Persistencia de Datos:** Sistema de almacenamiento óptimo basado puramente en ficheros[cite: 1]. Se ha descartado el uso de SQL o bases de datos relacionales tradicionales para implementar una gestión de I/O propia y a medida[cite: 1].
*   **Gestión de Errores:** Implementación de mecanismos de tolerancia a fallos para asegurar la continuidad del sistema[cite: 1].
*   **Multiplataforma:** Compatible con sistemas operativos Linux, Windows y macOS[cite: 1].

## 👥 Roles y Funcionalidades

El sistema está diseñado bajo una estricta jerarquía de acceso seguro, soportando tres tipos de usuarios con responsabilidades específicas[cite: 1]:

### 1. Gestor[cite: 1]
*   Responsable de dar de alta y administrar todos los elementos del aeropuerto (pistas, terminales, puertas de embarque, hangares y zonas de aparcamiento)[cite: 1].
*   Gestión y emisión de órdenes de pago mensuales, con integración de servicios externos para la generación de facturas en formato PDF[cite: 1].
*   Aprobación o denegación de solicitudes de vuelo, comprobando la disponibilidad de recursos en rangos de tiempo específicos (ej. ±30 minutos para aparcamientos/fingers)[cite: 1].
*   Acceso a estadísticas avanzadas sobre el uso de las instalaciones[cite: 1].

### 2. Operador (Aerolíneas)[cite: 1]
*   Programación de vuelos (mercancías o pasajeros) con un sistema de validación de estado (en hora/retrasado)[cite: 1].
*   Solicitud y gestión de vuelos compartidos entre distintas aerolíneas[cite: 1].
*   Control de los procesos de carga/descarga y embarque/desembarque[cite: 1].
*   Registro de nuevos aviones y tipos de aeronaves en el sistema[cite: 1].

### 3. Controlador[cite: 1]
*   Redirección de aeronaves y asignación de pistas de aterrizaje/despegue, zonas de espera, hangares o fingers garantizando la disponibilidad de recursos[cite: 1].
*   Gestión de las señales de aterrizaje, previniendo colisiones lógicas en las pistas[cite: 1].
*   Monitorización en tiempo real mediante notificaciones sobre aeronaves sobrevolando o aproximándose a la terminal[cite: 1].

## ⚙️ Instalación y Ejecución

Al ser un proyecto desarrollado en Java, su ejecución es sencilla mediante cualquier IDE compatible (como Eclipse):

1. Clona este repositorio en tu máquina local:
```bash
   git clone [https://github.com/TU-USUARIO/SkyCompass.git](https://github.com/TU-USUARIO/SkyCompass.git)
