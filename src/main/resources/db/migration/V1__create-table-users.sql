CREATE TABLE users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT,
    Cpf TEXT,
    nome TEXT,
    datanascimento TEXT,
    chavepix TEXT
);