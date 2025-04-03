# 📚 Proyecto Biblioteca

![image](https://github.com/user-attachments/assets/187de02b-f7bb-458e-ab59-4db504f8a19f)

## 📌 Descripción
Este proyecto tiene como objetivo modernizar la biblioteca del barrio mediante un sistema que permita gestionar los libros de manera eficiente. A través de una aplicación en terminal, la administradora podrá realizar operaciones CRUD sobre un inventario de libros almacenado en una base de datos PostgreSQL.

## 🚀 Tecnologías Utilizadas
- **Lenguaje de programación:** Java 21
- **Base de datos:** PostgreSQL
- **Gestión de dependencias:** Maven
- **Entorno de desarrollo:** Visual Studio Code
- **Control de versiones:** Git / GitHub
- **Gestión de tareas:** Jira

## ⚙️ Funcionalidades
- 📋 **Listar libros**: Mostrar todos los libros registrados en la base de datos.
- ➕ **Añadir libros**: Permitir la inserción de nuevos libros con sus atributos.
- ✏️ **Actualizar libros**: Modificar los datos de un libro existente.
- 🗑 **Eliminar libros**: Borrar un libro del sistema.
- 🔍 **Buscar libros**:
  - Por título
  - Por autor
  - Por género literario

## 📂 Estructura del Proyecto
El proyecto sigue una estructura organizada con Maven:
```
Biblioteca/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── models/
│   │   │   ├── services/
│   │   │   ├── controllers/
│   │   ├── resources/
│   ├── test/
│── pom.xml
│── README.md
```

## 🛠 Instalación y Configuración
1. **Clonar el repositorio**:
   ```sh
   git clone https://github.com/usuario/proyecto-biblioteca.git
   ```
2. **Configurar PostgreSQL**:
   - Crear una base de datos llamada `biblioteca_db`.
   - Configurar las credenciales en el archivo de conexión.
   - Asegurarse de que el nombre de la db sea lo mismo cuando se conecte al código.
3. **Compilar y ejecutar el proyecto**:
   ```sh
   mvn clean install
   java -jar target/biblioteca.jar
   ```

## 🔒 Consideraciones de Seguridad
- Uso de consultas preparadas para prevenir inyección SQL.
- Configuración de permisos en la base de datos.
- Implementación de validaciones de entrada.

## 👥 Metodología de Trabajo
El desarrollo se basa en metodologías ágiles con un único sprint. Se recomienda seguir buenas prácticas de control de versiones y mantener una comunicación activa dentro del equipo.

## 📌 Autores
- Carol Mas: PO/CODER.
- Guadalupe Hani: SM/CODER.
- Miriam Sánchez: CODER.
- Priscila Guillén: CODER.
- Einar Sánchez: CODER.

## 📜 Licencia
Este proyecto se distribuye bajo la licencia MIT. Puedes usarlo y modificarlo libremente.

