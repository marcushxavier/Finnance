CREATE TABLE users (
    id uuid NOT NULL PRIMARY KEY,
    name character varying(255),
    email character varying(255) NOT NULL UNIQUE,
    password character varying(255) NOT NULL
);
CREATE TABLE notes(
   id SERIAL PRIMARY KEY,
   owner_id UUID NOT NULL REFERENCES users (id),
   title VARCHAR(255) NOT NULL,
   value INT NOT NULL,
   is_outflow BOOLEAN,
   category VARCHAR(100),
   date DATE NOT NULL
)