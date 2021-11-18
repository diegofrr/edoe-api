# eDoe.com
Projeto referente à disciplina de **Desenvolvimento de Sistemas Corporativo** orientada pela professora **Raquel Vigolvino** do curso de Sistemas de Informação - UFPB - Campus IV.

Discente: Diêgo Raian da Silva Ferreira


## 1. Descrição

Muitas pessoas tem interesse em fazer doações, mas as vezes não tem o tempo necessário para encontrar onde doar ou como doar. Como sabemos, vivemos em um país em que a desigualdade social é muito alta e por isso há muitas pessoas necessitadas, ainda mais agora em plena pandemia… De um lado pessoas que querem fazer algo para colaborar nesse momento tão delicado e por outro pessoas que realmente estão precisando de um apoio. Precisamos de um sistema para dar suporte a essa rede de doações: o eDoe.com. 

No eDoe.com usuários de todo o campus IV da UFPB podem cadastrar itens a serem doados. Usuários da região ou do próprio campus devem poder cadastrar itens que gostariam de receber como doação (necessidades) e o sistema deve saber casar doações com necessidades para facilitar a doação. Uma vez fechada uma doação, o sistema ajuda a organizar o encontro de doador/receptor para a finalização.

## 2. Links úteis
[Vídeo explicativo](https://youtube.com)

[Documentação Swagger](https://edoe-api.herokuapp.com/swagger-ui.html)

[Postman Colletion - JSON format](https://www.getpostman.com/collections/075d5afe1b7f0ef552ed) (editando...)

[Banco H2](https://edoe-api.herokuapp.com/h2)
```
Saved Settings: Generic H2 (Embedded)
Setting Name: Generic H2 (Embedded)
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:~/dados
User Name: admin
Password: admim
```

## 3. Segurança

Foi utilizado o JSON Web Token (JWT), sendo necessário informar um token de acesso para realizar determinadas requisições. 
O token de acesso é gerado quando o usuário faz login no sistema e tem duração de 60 minutos, por consequência, o sistema exige novamente as credenciais do usuário após o término deste tempo.

### 3.1. Realizando login no sistema, recebendo um token de acesso e utilizando-o
/i\ De preferência, utilize o Postman para realizar os passos a seguir:
```
Passo 1 - Se redirecione para https://edoe-api/herokuapp.com/api/auth/login;
Passo 2 - Faça um POST passando no body da requisição um JSON com email e senha -> {"email": "...", "senha": "..."};
Passo 3 - Caso as credenciais seja válidas, o sistema retorna o token de acesso que deve ser copiado;
Passo 4 - Na aba "Auth" selecione Bearer Token como o tipo de autenticação e forneça seu token de acesso gerado.
```




## ... Readme em construção ... 🚧
