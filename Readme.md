# 📚 Tabla `books` - Guía de Interacción

Este documento explica cómo interactuar con la tabla `books` de la base de datos. Incluye ejemplos de operaciones CRUD (Create, Read, Update, Delete) y consultas avanzadas.

---

## **Estructura de la tabla `books`**

| Campo         | Tipo           | Descripción                                                               |
| ------------- | -------------- | ------------------------------------------------------------------------- |
| `id`          | `integer`      | Identificador único del libro (clave primaria, generado automáticamente). |
| `title`       | `varchar(100)` | Título del libro (obligatorio).                                           |
| `author`      | `text[]`       | Lista de autores del libro (obligatorio).                                 |
| `description` | `varchar(200)` | Breve descripción del libro (obligatorio).                                |
| `isbn`        | `bigint`       | Número ISBN único del libro (obligatorio).                                |
| `gender`      | `text[]`       | Lista de géneros literarios del libro (obligatorio).                      |
| `pages`       | `integer`      | Número de páginas del libro (obligatorio).                                |

---

## **Operaciones CRUD**

### **1. CREATE (Insertar registros)**

Para agregar un nuevo libro a la tabla, utiliza la instrucción `INSERT INTO`:

```sql
INSERT INTO public.books (title, author, description, isbn, gender, pages)
VALUES
('El Principito', ARRAY['Antoine de Saint-Exupéry'], 'Un clásico de la literatura infantil', 9781234567890, ARRAY['Ficción', 'Infantil'], 96);
```

### **2. READ (Consultar registros)**

Consulta para obtener todos los registros de la tabla, utiliza la instrucción `SELECT * FROM`:

```sql
SELECT * FROM books;
```

### **2.b. Obtener un libro por ID**

Consulta para obtener un libro específico utilizando su id, utiliza la instrucción `SELECT * FROM & WHERE`:

```sql
SELECT * FROM public.books WHERE id = 2;
```

### **2.c. Buscar libros por título**

Consulta para buscar libros cuyo título contenga una palabra específica, utiliza la instrucción `SELECT * FROM & WHERE & ILIKE`:

```sql
SELECT * FROM books WHERE title ILIKE '%principito%';
```

### **2.d. Buscar libros por género**

Consulta para buscar libros que pertenezcan a un género específico, utiliza la instrucción `SELECT * FROM & WHERE & ANY`:

```sql
SELECT * FROM books WHERE 'Ficción' = ANY (gender);
```

### **2.e. Buscar libros por autor**

Consulta para buscar libros escritos por un autor específico, utiliza la instrucción `SELECT * FROM & WHERE & ANY`:

```sql
SELECT * FROM books  WHERE 'Antoine de Saint-Exupéry' = ANY (author);
```

### **3. UPDATE (Actualizar registros)**

Para actualizar la información de un libro, utiliza la instrucción `UPDATE & SET & WHERE`:

```sql
UPDATE public.books
SET
    title = 'El Principito (Edición Especial)',
    description = 'Una edición especial del clásico de la literatura infantil',
    pages = 100
WHERE id = 1;
```

### **4. DELETE (Eliminar registros)**

Para eliminar un libro de la tabla, utiliza la instrucción: `DELETE FROM & WHERE`:

```sql
DELETE FROM public.books WHERE id = 1;
```
