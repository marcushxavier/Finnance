ALTER TABLE notes ALTER COLUMN is_outflow TYPE INTEGER USING is_outflow::integer;
ALTER TABLE notes RENAME is_outflow TO flow;