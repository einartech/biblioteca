GET ALL BOOKS example:
SELECT \* FROM books

GET one BOOK example:
SELECT \* FROM public.books
WHERE id = 2;

INSERT example:

INSERT INTO public.books (title, author, description, isbn, gender, pages)
VALUES
('The Great PEPA', ARRAY['F. Scott PEPA'], 'A classic PEPA set in the Jazz Age.', 9780743273565, ARRAY['PEPA', 'Classic'], 180);

DELETE example:

DELETE FROM public.books
WHERE id = 4;

UPDATE example:

UPDATE public.books
SET
title = 'TAAAAAAA',
author = ARRAY['F. TAAAAAAA PEPA'],
description = 'A TAAAAAAA PEPA set in TAAAAAAA the Jazz Age.',
isbn = 9780743273565,
gender = ARRAY['PEPA', 'Classic'],
pages = 180
WHERE id = 5;
