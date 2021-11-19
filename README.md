# eDoe.com
Projeto referente à disciplina de **Desenvolvimento de Sistemas Corporativo** orientada pela professora **Raquel Vigolvino** do curso de Sistemas de Informação - UFPB - Campus IV.

Discente: Diêgo Raian da Silva Ferreira

## 1. Descrição

Muitas pessoas tem interesse em fazer doações, mas as vezes não tem o tempo necessário para encontrar onde doar ou como doar. Como sabemos, vivemos em um país em que a desigualdade social é muito alta e por isso há muitas pessoas necessitadas, ainda mais agora em plena pandemia… De um lado pessoas que querem fazer algo para colaborar nesse momento tão delicado e por outro pessoas que realmente estão precisando de um apoio. Precisamos de um sistema para dar suporte a essa rede de doações: o eDoe.com. 

No eDoe.com usuários de todo o campus IV da UFPB podem cadastrar itens a serem doados. Usuários da região ou do próprio campus devem poder cadastrar itens que gostariam de receber como doação (necessidades) e o sistema deve saber casar doações com necessidades para facilitar a doação. Uma vez fechada uma doação, o sistema ajuda a organizar o encontro de doador/receptor para a finalização.

[Mais...](https://docs.google.com/document/d/1S2nt77eHhDRmc7L2CtRLEKP1rGfASmUscaRQE94ZHYw/edit#)

## 2. Links úteis
2.1. [URL base](https://edoe-api.herokuapp.com/api/)

2.2. [Vídeo explicativo](https://youtube.com)

2.3. [Documentação Swagger](https://edoe-api.herokuapp.com/swagger-ui.html)

2.4. [Postman Colletion - JSON format](https://www.getpostman.com/collections/075d5afe1b7f0ef552ed)

2.5. [Banco H2](https://edoe-api.herokuapp.com/h2)
```
Saved Settings: Generic H2 (Embedded)
Setting Name: Generic H2 (Embedded)
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:~/dados
User Name: admin
Password: admin
```

## 3. Intalação e utilização
Método #1 - Devido ao sistema já está implementado no **Heroku**, você pode simplesmente utilizar a [URL base](https://edoe-api.herokuapp.com/api/) informada mais acima e, com o auxílio da documentação Swagger para se direcionar entre as rotas existentes, realizar requisições ao sistema.

Método #2 - Antes de tudo, faça o clone do repositório ou baixe o arquivo zipado do mesmo. Em seguida, dentro da pasta principal do projeto, basta executar o seguinte comando:
```
mvn spring-boot:run
```
Pronto! Após ser feita a instalação automática de algumas dependências, o sistema já estará rodando em ```localhost:8080/``` (esta será a URL base para a utilização do sistema)

### 3.1 Importando coleção no Postman
Faça a importação da coleção já com todas as rotas e métodos atribuídos para agilizar o processo de realização das requisições.

```
Passo 1 - Dentro do Postman, vá até a opção de Import nas suas coleções;
Passo 2 - Na janela de Import, vá até a aba "Link";
Passo 3 - Informe o link do Postman Colletion (item 2.4) informado no início e clique em Continue.
Obs.: Os itens nomeados com um cadeado significam que necessitam de autenticação (veja o item 4.1)
```

## 4. Segurança

Foi utilizado o JSON Web Token (JWT), sendo necessário informar um token de acesso para realizar determinadas requisições. 
O token de acesso é gerado quando o usuário faz login no sistema e tem duração de 60 minutos, por consequência, o sistema exige novamente as credenciais do usuário após o término deste tempo.

### 4.1. Realizando login no sistema, recebendo um token de acesso e utilizando-o
/i\ Os passos a seguir foram realizados através do Postman.
```
Passo 1) Se redirecione para https://edoe-api/herokuapp.com/api/auth/login;
Passo 2) Faça um POST passando no body da requisição um JSON com email e senha;
Passo 3) Caso as credenciais seja válidas, o sistema retorna o token de acesso que deve ser copiado;
Passo 4) Na aba "Auth" selecione Bearer Token e forneça seu token de acesso gerado.
```
