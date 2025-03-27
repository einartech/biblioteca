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
