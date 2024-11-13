CREATE TABLE users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL,
    Cpf TEXT NOT NULL,
    nome TEXT NOT NULL,
    datanascimento TEXT NOT NULL,
    chavepix TEXT NOT NULL
);