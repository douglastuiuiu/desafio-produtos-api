#PRODUCTS-API

A idéia era simular a integração de uma API com outra porém sem a necessidade de informar para a API que detém a informação original, quais as outras APIs que querem receber as notificações e dados originários da API de origem.

Como implementação técnica, utilizei spring-boot para APIs e RabbitMQ para implementar o padrão de event-sourcing.
Como requisito, o RabbitMQ deve estar instalado e em execução com o usuário e senha guest.

Para inicializar a api, executar na raiz do projeto o comando: mvn clean spring-boot:run

Após a inicialização do serviço, a aplicação poderá receber chamadas nos seguintes endpoits:
- GET: http://localhost:8080/api/v1/health
- GET: http://localhost:8080/api/v1/h2-console
- GET: http://localhost:8080/api/v1/products
- GET: http://localhost:8080/api/v1/products/33333333
- POST: http://localhost:8080/api/v1/products
        { "id": "33333333", "name": "TV", "kind": "Eletro", "manufacturer": "LG" }
- PUT: http://localhost:8080/api/v1/products/33333333
        { "id": "33333333", "name": "TV LED", "kind": "Eletro", "manufacturer": "LG+" }
- DELETE: http://localhost:8080/api/v1/products/33333333
