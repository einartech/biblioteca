INSERT example:

INSERT INTO public.books (title, author, description, isbn, gender, pages)
VALUES
('The Great PEPA', ARRAY['F. Scott PEPA'], 'A classic PEPA set in the Jazz Age.', 9780743273565, ARRAY['PEPA', 'Classic'], 180);

DELETE example:
DELETE FROM public.books
WHERE id = 4;
