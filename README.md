# library-system-java

## Objetivo
Desenvolver um sistema de console para gerenciar o empréstimo de livros de uma biblioteca.

O programa deverá interagir com o usuário, permitindo que ele veja os livros disponíveis e realize o empréstimo de um deles.

Como deve funcionar
* Início: O programa começará, em loop, perguntando ao usuário se ele deseja ver a lista de livros disponíveis.
* Listagem: Se a resposta for "SIM", o sistema exibirá no console uma lista com os livros que não foram emprestados.
* Empréstimo: Após ver a lista, o usuário poderá escolher um livro pelo seu id, informar o próprio nome e, assim, registrar o empréstimo. O sistema deve confirmar a operação com uma mensagem de sucesso e marcar o livro como indisponível.
* Encerramento: Se a resposta inicial for "NÃO", ou após a conclusão de um empréstimo, o sistema deve exibir uma mensagem de despedida e finalizar a execução.


Estrutura do Projeto
O projeto está organizado em pacotes (`model`, `repository`, `db`) e os dados são persistidos em um banco Postgres. Aqui estão as classes principais e suas funcionalidades:

### Livro

  1 - id: Identificador único do livro
  
  2 - titulo: Título do livro
  
  3 - idAutor: Referência ao autor do livro (chave estrangeira para `Autor`)
  
  4 - status: Indica se o livro está `DISPONIVEL` ou `INDISPONIVEL` para empréstimo (`LivroEnum`)
  
  5 - dataCadastro: Data que o livro foi cadastrado
  
  6 - dataAtualizacao: Data que o livro foi atualizado


### Autor

  1 - id: Identificador único do autor

  2 - nome: Nome do autor

  3 - dataNascimento: Nascimento do autor


### Emprestimo

  1 - id: Identificador único do empréstimo

  2 - idLivro: Referência ao livro que foi emprestado (chave estrangeira para `Livro`)

  3 - nomeCliente: Nome do cliente que pegou o livro emprestado

  4 - dataEmprestimo: Data que o empréstimo foi realizado

  5 - dataDevolucao: Data que o livro foi devolvido (pode ser nula se ainda não foi devolvido)


### Biblioteca

Classe de fachada que orquestra as operações de negócio (cadastro, empréstimo, devolução), delegando o acesso a dados para os repositories:

  1 - livroRepository: Acesso a dados de livros

  2 - autorRepository: Acesso a dados de autores

  3 - emprestimoRepository: Acesso a dados de empréstimos

## Como rodar o projeto

### Pré-requisitos
- JDK 17 ou superior instalado
- Maven instalado
- Uma instância Postgres acessível (local ou remota)

### Configuração
Antes de rodar o projeto, crie um arquivo `.env` na raiz com as credenciais do seu banco Postgres:
```
DB_URL=jdbc:postgresql://localhost:5432/biblioteca
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
```
Ao rodar, o Flyway aplica automaticamente as migrations em `src/main/resources/db/migration` e cria as tabelas necessárias.

### Opção 1 — via IntelliJ IDEA (recomendado)
1. Clone o repositório:
```bash
   git clone https://github.com/daryoandrade-art/library-system-java.git
```
2. Abra a pasta no IntelliJ IDEA (`File → Open`)
3. Marque `src/main/java` como Sources Root, se necessário (`botão direito → Mark Directory as → Sources Root`)
4. Execute a classe `Main.java` (botão direito → `Run 'Main.main()'`)

### Opção 2 — via terminal (Maven)
```bash
git clone https://github.com/daryoandrade-art/library-system-java.git
cd library-system-java
mvn compile
mvn exec:java -Dexec.mainClass="biblioteca.Main"
```

O programa roda em modo console — todas as interações acontecem via terminal, sem interface gráfica.


## Status do projeto

Este projeto começou como o desafio "Sistema de Biblioteca" da Rocketseat e está sendo evoluído com funcionalidades adicionais:

- [x] CRUD de livros, autores e empréstimos
- [x] Menu interativo via console
- [x] Persistência de dados em banco Postgres com JDBC e migrations via Flyway
- [ ] Multa por atraso na devolução
- [ ] Busca por autor/categoria
- [ ] Testes unitários (JUnit)
