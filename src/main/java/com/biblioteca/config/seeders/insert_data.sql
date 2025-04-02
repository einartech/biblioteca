-- Insertar 20 registros en la tabla books
INSERT INTO public.books (title, author, description, isbn, genre, pages, publisher, year)
VALUES
('El Principito', ARRAY['Antoine de Saint-Exupéry'], 'Un clásico de la literatura infantil', 9781234567890, ARRAY['Ficción', 'Infantil'], 96, 'Reynal & Hitchcock', 1943),
('Cien Años de Soledad', ARRAY['Gabriel García Márquez'], 'Una obra maestra del realismo mágico', 9789876543210, ARRAY['Ficción', 'Realismo Mágico'], 417, 'Editorial Sudamericana', 1967),
('1984', ARRAY['George Orwell'], 'Una distopía sobre un régimen totalitario', 9781231231231, ARRAY['Ficción', 'Distopía'], 328, 'Secker & Warburg', 1949),
('Orgullo y Prejuicio', ARRAY['Jane Austen'], 'Una novela romántica clásica', 9784564564564, ARRAY['Romance', 'Clásico'], 279, 'T. Egerton', 1813),
('Matar a un Ruiseñor', ARRAY['Harper Lee'], 'Una historia sobre la justicia y la moralidad', 9787897897897, ARRAY['Ficción', 'Drama'], 281, 'J.B. Lippincott & Co.', 1960),
('El Hobbit', ARRAY['J.R.R. Tolkien'], 'Una aventura épica en la Tierra Media', 9781111111111, ARRAY['Fantasía', 'Aventura'], 310, 'George Allen & Unwin', 1937),
('Don Quijote de la Mancha', ARRAY['Miguel de Cervantes'], 'Una de las obras más importantes de la literatura española', 9782222222222, ARRAY['Ficción', 'Clásico'], 863, 'Francisco de Robles', 1605),
('Crimen y Castigo', ARRAY['Fiódor Dostoyevski'], 'Un análisis psicológico de la culpa y la redención', 9783333333333, ARRAY['Ficción', 'Drama'], 671, 'The Russian Messenger', 1866),
('El Gran Gatsby', ARRAY['F. Scott Fitzgerald'], 'Una crítica al sueño americano', 9784444444444, ARRAY['Ficción', 'Drama'], 180, 'Charles Scribner''s Sons', 1925),
('La Odisea', ARRAY['Homero'], 'Una epopeya clásica de la literatura griega', 9785555555555, ARRAY['Clásico', 'Aventura'], 541, 'Desconocido', -800),
('La Divina Comedia', ARRAY['Dante Alighieri'], 'Un viaje épico a través del Infierno, el Purgatorio y el Paraíso', 9786666666666, ARRAY['Clásico', 'Poesía'], 798, 'Desconocido', 1320),
('El Alquimista', ARRAY['Paulo Coelho'], 'Una historia sobre la búsqueda de los sueños', 9787777777777, ARRAY['Ficción', 'Filosofía'], 208, 'HarperCollins', 1988),
('Harry Potter y la Piedra Filosofal', ARRAY['J.K. Rowling'], 'El inicio de la saga mágica de Harry Potter', 9788888888888, ARRAY['Fantasía', 'Aventura'], 223, 'Bloomsbury', 1997),
('El Señor de los Anillos: La Comunidad del Anillo', ARRAY['J.R.R. Tolkien'], 'La primera parte de la épica trilogía', 9789999999999, ARRAY['Fantasía', 'Aventura'], 423, 'George Allen & Unwin', 1954),
('El Código Da Vinci', ARRAY['Dan Brown'], 'Un thriller lleno de misterios y conspiraciones', 9781010101010, ARRAY['Ficción', 'Thriller'], 489, 'Doubleday', 2003),
('Los Juegos del Hambre', ARRAY['Suzanne Collins'], 'Una distopía sobre la lucha por la supervivencia', 9781212121212, ARRAY['Ficción', 'Distopía'], 374, 'Scholastic Press', 2008),
('La Sombra del Viento', ARRAY['Carlos Ruiz Zafón'], 'Un misterio literario en la Barcelona de posguerra', 9781313131313, ARRAY['Ficción', 'Misterio'], 565, 'Planeta', 2001),
('El Nombre del Viento', ARRAY['Patrick Rothfuss'], 'La historia de un héroe contada por él mismo', 9781414141414, ARRAY['Fantasía', 'Aventura'], 662, 'DAW Books', 2007),
('Drácula', ARRAY['Bram Stoker'], 'El clásico de terror sobre el vampiro más famoso', 9781515151515, ARRAY['Terror', 'Clásico'], 418, 'Archibald Constable and Company', 1897),
('Frankenstein', ARRAY['Mary Shelley'], 'La historia del científico que creó un monstruo', 9781616161616, ARRAY['Terror', 'Clásico'], 280, 'Lackington, Hughes, Harding, Mavor & Jones', 1818);