CREATE TABLE emprestimo(
    id SERIAL PRIMARY KEY,
    livro_id INTEGER NOT NULL REFERENCES livro(id),
    nome_cliente VARCHAR(255) NOT NULL,
    data_emprestimo TIMESTAMP NOT NULL DEFAULT NOW(),
    data_devolucao TIMESTAMP NULL
)