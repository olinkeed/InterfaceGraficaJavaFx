CREATE DATABASE padaria_db;
\c padaria_db;

CREATE TABLE IF NOT EXISTS fornecedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    cidade VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco NUMERIC(10,2) NOT NULL CHECK (preco >= 0),
    quantidade INT NOT NULL DEFAULT 0 CHECK (quantidade >= 0),
    id_fornecedor INT REFERENCES fornecedor(id) ON DELETE SET NULL
);

INSERT INTO fornecedor (nome, telefone, email, cidade) VALUES
('Padaria São João', '11999999999', 'padaria@gmail.com', 'São Paulo'),
('Distribuidora Pão Bom', '11988888888', 'paobom@gmail.com', 'Campinas'),
('Doces da Serra', '21977777777', 'docesserra@gmail.com', 'Rio de Janeiro')
ON CONFLICT DO NOTHING;

INSERT INTO produto (nome, preco, quantidade, id_fornecedor) VALUES
('Pão Francês', 0.80, 300, 1),
('Bolo de Fubá', 15.00, 10, 2),
('Rosquinha Doce', 2.50, 40, 3)
ON CONFLICT DO NOTHING;
