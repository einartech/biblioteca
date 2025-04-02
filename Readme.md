# 📚 Biblioteca - Sistema de Gestión de Libros

Este proyecto es un sistema de gestión de libros diseñado para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en una base de datos. Está construido con Java y utiliza una arquitectura modular para garantizar la escalabilidad y el mantenimiento del código.

---

## **Índice**

1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Características Principales](#características-principales)
3. [Arquitectura del Proyecto](#arquitectura-del-proyecto)
4. [Requisitos Previos](#requisitos-previos)
5. [Instalación](#instalación)
6. [Uso del Proyecto](#uso-del-proyecto)
7. [Ejemplos de Código](#ejemplos-de-código)
8. [Herramientas Utilizadas](#herramientas-utilizadas)
9. [Equipo de Desarrollo](#equipo-de-desarrollo)
10. [Contribuciones](#contribuciones)

---

## **Descripción del Proyecto**

El sistema de biblioteca permite gestionar libros almacenados en una base de datos PostgreSQL. Los usuarios pueden realizar operaciones como agregar nuevos libros, buscar libros por título, género o autor, actualizar información de libros existentes y eliminarlos. Además, el sistema registra logs detallados para cada operación, lo que facilita el monitoreo y la depuración.

---

## **Características Principales**

- **Gestión de Libros:**
  - Crear, leer, actualizar y eliminar libros.
  - Búsqueda avanzada por título, género o autor.
- **Logs Detallados:**
  - Cada operación se registra en archivos de logs específicos para facilitar el monitoreo.
- **Interfaz de Consola:**
  - Interacción sencilla a través de un menú en la terminal.
- **Arquitectura Modular:**
  - Separación clara de responsabilidades entre controladores, vistas, modelos y configuración.

---

## **Arquitectura del Proyecto**

El proyecto sigue una arquitectura modular basada en el patrón **MVC (Modelo-Vista-Controlador)**:

1. **Modelo (`BookDAO`, `DBManager`):**

   - Gestiona la interacción con la base de datos.
   - Realiza operaciones CRUD en la tabla `books`.

2. **Vista (`BookView`):**

   - Proporciona una interfaz de usuario en la terminal.
   - Recoge datos del usuario y muestra resultados.

3. **Controlador (`BookController`):**

   - Actúa como intermediario entre la vista y el modelo.
   - Contiene la lógica de negocio.

4. **Configuración (`LoggerConfig`, `.env`):**
   - Maneja la configuración de logs y variables de entorno.

---

## **Requisitos Previos**

Antes de instalar el proyecto, asegúrate de tener lo siguiente:

- **Java 17** o superior.
- **PostgreSQL** instalado y configurado.
- **Maven** para gestionar dependencias.
- **Archivo `.env`** con las siguientes variables:
  ```plaintext
  DB_URL=jdbc:postgresql://localhost:5432/biblioteca
  DB_USER=tu_usuario
  DB_PASS=tu_contraseña
  ```

---

## **Instalación**

Sigue estos pasos para instalar y ejecutar el proyecto:

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/tu_usuario/biblioteca.git
   cd biblioteca
   ```

2. **Configura el archivo `.env`:**

   - Crea un archivo `.env` en la raíz del proyecto con las variables de conexión a la base de datos.

3. **Instala las dependencias:**

   ```bash
   mvn install
   ```

4. **Ejecuta el proyecto:**
   ```bash
   mvn exec:java -Dexec.mainClass="com.biblioteca.App"
   ```

---

## **Uso del Proyecto**

Al ejecutar el proyecto, se mostrará un menú en la terminal con las siguientes opciones:

1. Ver todos los libros.
2. Crear un nuevo libro.
3. Actualizar un libro existente.
4. Eliminar un libro.
5. Buscar libros por título.
6. Buscar libros por autor.
7. Buscar libros por género.

Selecciona una opción ingresando el número correspondiente.

---

## **Ejemplos de Código**

### **1. Crear un Libro**

```java
Book book = new Book(
    "El Principito",
    Arrays.asList("Antoine de Saint-Exupéry"),
    "Un clásico de la literatura infantil",
    9781234567890L,
    Arrays.asList("Ficción", "Infantil"),
    96,
    1943,
    "Reynal & Hitchcock"
);
bookController.createBook(book);
```

### **2. Buscar Libros por Título**

```java
List<Book> books = bookController.searchBookByTitle("Principito");
books.forEach(System.out::println);
```

### **3. Actualizar un Libro**

```java
Book book = new Book(
    "El Principito (Edición Especial)",
    Arrays.asList("Antoine de Saint-Exupéry"),
    "Una edición especial del clásico",
    9781234567890L,
    Arrays.asList("Ficción", "Infantil"),
    100,
    1943,
    "Reynal & Hitchcock"
);
book.setId(1); // ID del libro a actualizar
bookController.updateBook(book);
```

---

## **Herramientas Utilizadas**

- **Java 17:** Lenguaje principal del proyecto.
- **PostgreSQL:** Base de datos relacional para almacenar los libros.
- **Maven:** Gestión de dependencias y construcción del proyecto.
- **dotenv-java:** Manejo de variables de entorno.
- **Java Logging API:** Registro de logs en archivos y consola.

---

## **Equipo de Desarrollo**

- **Nombre del Desarrollador 1:** Rol (e.g., Backend Developer)
- **Nombre del Desarrollador 2:** Rol (e.g., Database Specialist)
- **Nombre del Desarrollador 3:** Rol (e.g., QA Engineer)

---

## **Contribuciones**

¡Las contribuciones son bienvenidas! Si deseas contribuir:

1. Haz un fork del repositorio.
2. Crea una rama para tu funcionalidad o corrección:
   ```bash
   git checkout -b mi-nueva-funcionalidad
   ```
3. Realiza tus cambios y haz un commit:
   ```bash
   git commit -m "Agregué una nueva funcionalidad"
   ```
4. Envía un pull request.

---

## **Licencia**

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
