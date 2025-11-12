# 🎲 Juego de Parchís - Arquitectura Blackboard

Este repositorio contiene la implementación y los diagramas UML del **juego de Parchís** desarrollado con una arquitectura basada en el patrón **Blackboard**.  
El sistema permite la conexión de múltiples jugadores, la gestión de turnos, y la comunicación entre cliente-servidor.

---

## 📘 Diagramas UML

A continuación se muestran los **5 diagramas UML** con sus respectivas descripciones.

---

### 1️⃣ Diagrama de Casos de Uso

![Diagrama de Casos de Uso](docs/casos_de_uso.png)

**Descripción:**
Este diagrama representa las interacciones principales entre los **jugadores** y el **sistema**.  
Los casos de uso reflejan las acciones clave del juego, como conectarse, tirar el dado, mover fichas y recibir notificaciones del servidor.

**Actores:**
- `Jugador`: Usuario que participa en la partida.
- `Servidor (Sistema)`: Gestiona las reglas, turnos y movimientos.

**Casos principales:**
- Conectarse al servidor  
- Iniciar partida  
- Tirar dado  
- Seleccionar ficha  
- Mover ficha  
- Recibir notificación  
- Mostrar mensaje  

---

### 2️⃣ Diagrama de Clases de Análisis

![Diagrama de Clases de Análisis](docs/clases_analisis.png)

**Descripción:**
Este diagrama identifica las clases y responsabilidades en el análisis del sistema, separando lógica, presentación y comunicación.  
El objetivo es representar **qué hace cada componente** sin entrar aún en detalles de implementación.

**Clases principales:**
- `ControladorCliente` — Coordina vista y modelo del cliente.  
- `ModeloJugador` — Contiene el estado del jugador.  
- `VistaGrafica` — Interfaz visual para el usuario.  
- `ServidorParchis` — Administra conexiones y flujo del juego.  
- `Blackboard` — Contiene la lógica central del juego.  
- `HiloJugador` — Gestiona la comunicación individual entre servidor y cliente.

---

### 3️⃣ Diagrama de Secuencia (Análisis)

![Diagrama de Secuencia - Análisis](docs/secuencia_analisis.png)

**Descripción:**
Este diagrama describe el flujo general del caso de uso “Tirar dado y mover ficha”.  
Muestra cómo las clases interactúan entre sí desde que el jugador lanza el dado hasta que se actualiza el estado del tablero.

---

### 4️⃣ Diagrama de Clases de Diseño

![Diagrama de Clases de Diseño](docs/clases_diseno.png)

**Descripción:**
Representa la estructura final del sistema según la implementación del patrón Blackboard.  
Aquí se detallan los módulos de conocimiento, los controladores y las relaciones concretas entre objetos.

---

### 5️⃣ Diagrama de Secuencia (Diseño)

![Diagrama de Secuencia - Diseño](docs/secuencia_diseno.png)

**Descripción:**
Este diagrama ilustra cómo las clases del diseño colaboran en tiempo de ejecución.  
Desde la acción del jugador hasta la actualización visual, pasando por la comunicación servidor-cliente y la actualización del Blackboard.
