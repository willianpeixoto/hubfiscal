# Hub Fiscal
Orquestrador que publica requisições de venda em uma fila, faz a leitura e processa a autorização da venda

####Java 11.0.10
####Maven 3.6.3
####Banco de dados H2
* `Usuário: sa`
* `Senha:`
####RabbitMQ:
* `docker run -d --name rabbitmq-demo -p 5672:5672 -p 5673:5673 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=rabbitmq -e RABBITMQ_DEFAULT_PASS=rabbitmq rabbitmq:3.8-management`
* `Usuário: rabbitmq`
* `Senha: rabbitmq`
* [Link de acesso ao RabbtMQ](http://localhost:15672/)
####Swagger
* [Link de acesso ao Swagger](http://localhost:8080/swagger-ui/index.html)
