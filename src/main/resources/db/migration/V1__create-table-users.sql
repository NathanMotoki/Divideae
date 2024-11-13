CREATE TABLE users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL,
    CPF TEXT NOT NULL,
    nome TEXT NOT NULL,
    dtNascimento DATE NOT NULL,
    chavePix TEXT NOT NULL
);