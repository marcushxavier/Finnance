CREATE TABLE IF NOT EXISTS users (
    id uuid NOT NULL PRIMARY KEY,
    name character varying(255),
    email character varying(255) NOT NULL UNIQUE,
    password character varying(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS notes(
   id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   owner_id UUID NOT NULL REFERENCES users (id),
   title VARCHAR(255) NOT NULL,
   value INT NOT NULL,
   flow INT,
   category VARCHAR(100),
   date DATE NOT NULL
);