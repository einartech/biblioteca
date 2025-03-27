-- Table: public.books

-- DROP TABLE IF EXISTS public.books;

CREATE TABLE IF NOT EXISTS public.books
(
id integer NOT NULL,
title character varying(100) COLLATE pg_catalog."default" NOT NULL,
author text[] COLLATE pg_catalog."default" NOT NULL,
description character varying(200) COLLATE pg_catalog."default" NOT NULL,
isbn bigint NOT NULL,
gender text[] COLLATE pg_catalog."default" NOT NULL,
pages integer NOT NULL,
CONSTRAINT books_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.books
OWNER to postgres;

//////////////

INSERT INTO public.books (id, title, author, description, isbn, gender, pages)
VALUES
(1, 'The Great Gatsby', ARRAY['F. Scott Fitzgerald'], 'A classic novel set in the Jazz Age.', 9780743273565, ARRAY['Fiction', 'Classic'], 180),
(2, 'To Kill a Mockingbird', ARRAY['Harper Lee'], 'A novel about racial injustice in the Deep South.', 9780061120084, ARRAY['Fiction', 'Drama'], 281),
(3, '1984', ARRAY['George Orwell'], 'A dystopian novel about totalitarianism.', 9780451524935, ARRAY['Fiction', 'Dystopian'], 328),
(4, 'Pride and Prejudice', ARRAY['Jane Austen'], 'A romantic novel about manners and marriage.', 9780141439518, ARRAY['Fiction', 'Romance'], 279),
(5, 'The Catcher in the Rye', ARRAY['J.D. Salinger'], 'A story about teenage rebellion and alienation.', 9780316769488, ARRAY['Fiction', 'Coming-of-Age'], 214),
(6, 'The Hobbit', ARRAY['J.R.R. Tolkien'], 'A fantasy adventure about a hobbit named Bilbo Baggins.', 9780547928227, ARRAY['Fiction', 'Fantasy'], 310),
(7, 'Moby-Dick', ARRAY['Herman Melville'], 'A novel about the quest to hunt a giant white whale.', 9781503280786, ARRAY['Fiction', 'Adventure'], 635),
(8, 'The Alchemist', ARRAY['Paulo Coelho'], 'A philosophical novel about following your dreams.', 9780061122415, ARRAY['Fiction', 'Philosophy'], 208),
(9, 'The Da Vinci Code', ARRAY['Dan Brown'], 'A mystery thriller involving secret societies.', 9780307474278, ARRAY['Fiction', 'Thriller'], 489),
(10, 'Harry Potter and the Sorcerers Stone', ARRAY['JK Rowling'], 'The first book in the Harry Potter series.', 9780590353427, ARRAY['Fiction', 'Fantasy'], 309);
