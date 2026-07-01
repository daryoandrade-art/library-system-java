Objetivo

Desenvolver um sistema de console para gerenciar o empréstimo de livros de uma biblioteca.

O programa deverá interagir com o usuário, permitindo que ele veja os livros disponíveis e realize o empréstimo de um deles.

Como deve funcionar
* Início: O programa começará, em loop, perguntando ao usuário se ele deseja ver a lista de livros disponíveis.
* Listagem: Se a resposta for "SIM", o sistema exibirá no console uma lista com os livros que não foram emprestados.
* Empréstimo: Após ver a lista, o usuário poderá escolher um livro pelo seu id, informar o próprio nome e, assim, registrar o empréstimo. O sistema deve confirmar a operação com uma mensagem de sucesso e marcar o livro como indisponível.
* Encerramento: Se a resposta inicial for "NÃO", ou após a conclusão de um empréstimo, o sistema deve exibir uma mensagem de despedida e finalizar a execução.


Estrutura do Projeto
O projeto será dividido em algumas classes simples para manter a organização. Aqui estão as classes principais e suas funcionalidades:

* Livro

  1 - id: Identificador único do livro

  2 - titulo: Título do livro

  3 - autor: Autor do livro (objeto do tipo Autor)

  4 - disponivel: Indica se o livro está disponível para empréstimo

  5 - dataCadastro: Data que o livro foi cadastrado

  6 - dataAtualização: Data que o livro foi atualizado


* Autor

  1 - id: Identificador único do autor

  2 - nome: Nome do autor

  3 - dataNascimento: Nascimento do autor


* Emprestimo

  1 - id: Identificador único do empréstimo

  2 - livro: Livro que foi emprestado (objeto do tipo Livro)

  3 - nomeCliente: Nome do cliente que pegou o livro emprestado

  4 - dataEmprestimo: Data que o empréstimo foi realizado

  5 - dataDevolucao: Data que o livro foi devolvido (pode ser nula se ainda não foi devolvido)


* Biblioteca

  1 - livros: Lista de livros na biblioteca

  2 - autores: Lista de autores da biblioteca

  3 - emprestimos: Lista de empréstimos da biblioteca