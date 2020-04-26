# AlugaLivro
Sistema para aluguel de livros

#

### Funcionalidades:
- Logar no sistema (iniciar sessao)
- Cadastrar livros (todo crud)
  - Alugar livro
##### Validação
- Para acessar os recursos da aplicação, antes deve ser realizado o login
- Não é possível modificar ou excluir um livro que está alugado.

#


## Tecnologias utilizadas:
- Linguagem: Java
- Framework: Spring
- Banco de Dados: SQLite
- Testes unitários: Junit, Mockito
- Lint: CheckStyle

#

### Endpoints:
POST /login
POST /logout

GET, POST /livros
GET, DELETE, PUT /livros/:id
POST /livros/:id/alugar

### Para ver detalhes dos endpoints e testar a API, acesse:
https://documenter.getpostman.com/view/11210696/SzfB17Yu?version=latest#0f61d235-23e1-4cd0-be4c-58eda54b62cc
