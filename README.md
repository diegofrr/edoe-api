# eDoe.com
Projeto referente à disciplina de **Desenvolvimento de Sistemas Corporativo** orientada pela professora **Raquel Vigolvino** do curso de Sistemas de Informação - UFPB - Campus IV.

Discente: Diêgo Raian da Silva Ferreira

#

## 1. Links úteis
[Vídeo explicativo](https://youtube.com)

[Documentação Swagger](https://edoe-api.herokuapp.com/swagger-ui.html)

[Postman Colletion - JSON format](https://www.getpostman.com/collections/075d5afe1b7f0ef552ed)

[Banco H2](https://edoe-api.herokuapp.com/h2)
```
Saved Settings: Generic H2 (Embedded)
Setting Name: Generic H2 (Embedded)
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:~/dados
User Name: admin
Password: admim
```

## 2. JSON's prontos para exemplificação
<img src="https://imgur.com/QyqvoZz.png"> **/api/usuarios/cadastro** -> Adicionar um novo usuário

```json
{
    "nome": "Fulano",
    "email": "fulano@gmail.com",
    "senha": "fulano123",
    "celular": 123456789,
    "classe": "PESSOA_FISICA",
    "docIdentificacao": 111222333,
    "tipo": "DOADOR"
}
```
#

<img src="https://imgur.com/QyqvoZz.png"> **/api/usuarios/login** -> Realizar login no sistema (retorna um  token de autenticação)

```json
{
    "email": "fulano@gmail.com",
    "senha": "fulano123",
}
```
O token de autenticação, tem duração de 15 minutos, assim, quando expirado este tempo, o usuário necessita realizar login novamente no sistema para poder utilizar certas funções. Acredita-se que 15 minutos é um tempo razoavelmente bom para os usuários poderem utilizar o sistema. Toda via, caso esta API fosse aplicada realmente, poderia haver um cálculo de tempo médio gasto pelos usuários para, assim, poder definir um tempo mais preciso.

#

<img src="https://imgur.com/QyqvoZz.png"> **/api/usuarios/tipos** (Necessita autenticação) -> Altera o Tipo  de um usuário.

```json
{
    "email": "fulano@gmail.com",
    "tipo": "DOADOR_RECEPTOR",
}
```
Os Tipos disponíveis no sistema são: DOADOR, RECEPTOR, DOADOR_RECEPTOR e ADMIN. Deve-se levar em consideração que SOMENTE administradores do sistema podem atribuir o Tipo ADMIN para outros usuários. Outro ponto bastante importante é que, caso não houver usuários cadastrados no sistema, o primeiro a ser cadastrado tem seu Tipo alterado para ADMIN automaticamente.


## ... Readme em construção ... 🚧
