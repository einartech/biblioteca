/////////create the table BOOKS/////////

-- DROP TABLE IF EXISTS public.books;

CREATE TABLE IF NOT EXISTS public.books
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    title character varying(100) COLLATE pg_catalog."default" NOT NULL,
    author text[] NOT NULL,
    description character varying(200) COLLATE pg_catalog."default" NOT NULL,
    isbn bigint NOT NULL,
    gender text[] NOT NULL,
    pages integer NOT NULL,
   
    CONSTRAINT books_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.books
    OWNER to postgres;


/////////INSERT into BOOKS/////////

INSERT INTO books (title, author, description, isbn, gender)
VALUES
();

