 # Source-2it-exam API
Para criaçao deste projeto foi utilizada a plataforma Java em sua versão 8 com Spring boot e PostgreSQL.

## Instalação e inicialicação da API
### Via Maven
```
git clone git@github.com:bernardolobato/source2it-api.git
cd source2it-api
```
* Altere as configuraçoes do seu banco de dados no application.properties

```
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```
- Inicie a aplicação
```
./mvnw spring-boot:run
```

### Via Docker
```
git clone git@github.com:bernardolobato/source2it-api.git
cd source2it-api
docker-compose up
```
- Com a versão via docker o ambiente inteiro é inicializado, incluindo aplicação Java, PostgreSQL e PgAdmin
Para acessar o PgAdmin: http://localhost:16543/
```
login: admin@admin.com
password: 123456
```
### Banco de dados e scripts de inicializaçao
- Em ambos os casos (maven e docker) o banco de dados é criado automaticamente pela aplicação.
- No entanto, dois scripts foram disponibilizados: schema.sql e init.sql, para criação da estrutura e carga inicial, respectivamente. Nenhum deles é estritamente necessário para o bom funcionamento da aplicação.
### Acessando a aplicação
- Uma vez inicializada, a api rodará no endereço: http://localhost:8080/
- Foi criada uma documentação através do Swagger no endereço: http://localhost:8080/swagger-ui.html

## Utilização
- O Primeiro passo para utilizar a api construída é através da criaçao de usuários. Utilize o endpoint correspondente através do Swagger ou similar ou, se o script de inicia;ização de dados foi utilizado, já existem dois usuários cadastrados: bernardolobato@gmail.com e admin@admin.com . Ambos com a senha 123456
- O próximo passo é ir até o endpoint de autenticação e inserir as credenciais de acordo com o usuário criado. Se o email e a senha estiverem corretos, a resposta da requisiçao será um token jwt. Este deve ser inserido no Header de todas as requisições, como um authorization token. No swagger pode ser adicionado através do botão Authorize (deve ser copiado inclusive a palavra 'Bearer')
- A partir de então, todos os endpoints estão disponiveis para uso com a devida autorizaçao concedida pelo jwt.

## Arquitetura do Projeto
- O projeto foi criado através da plataforma Java e Spring Boot, utilizando o banco de dados relacional PostgreSQL.
- A autenticação foi realizada através do Spring Security com token JWT (http://jwt.io), através da biblioteca jsonwebtoken (https://github.com/jwtk/jjwt)
- Abaixo temos uma imagem da arquitetura elaborada para o sistema. Na sequencia farei algumas considerações
![](https://github.com/bernardolobato/source2it-api/blob/master/documentacao/arquitetura.png?raw=true)

### Algumas considerações
- A imagem acima é uma livre adaptação do diagrama de componentes do modelo C4 de documentaçao arquitetural. Utilizar os quatro diagramas achei que seria demasiado prolixo, considerando o tamanho do projeto.

- Não foi utilizada uma camada de Services dentro da aplicação, pois como são somente CRUDs não julguei necessário, porém não exitaria em utilizar caso houvessem mais regras de negócio.

- Quaisquer esclarecimentos ou outras informaçoes estou a disposição. bernardolobato@gmail.com
