# springboot-rabbitmq
Projeto envolvendo 3 microservices, rabbitMQ e Mysql

## RabbitMQ
docker run --name=rabbitmq -d -p 5672:5672 -p 15672:15672  rabbitmq:3.8.5-management

Interface: http://localhost:15672/#/

Usuário e senha: guest

Criar filas (Queues):
 - filmesAvaliados
 - filmesAssistidos
 - suporteResposta
 - suporteTicket
 

## MYSQL
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=toor mysql/mysql-server:latest

Pra fins práticos subindo apenas um banco q serão utilizados por todos microserviços, porém os microserviços não acessam o dominio de outro serviço

### Caso apresente erro de conexão do spring com o mysql será necessário executar os comandos abaixo no docker do mysql

docker exec -it mysql bash
mysql -u root -p (digita a senha[configurado senha toor])

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'toor';
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'toor';



# Swagger dos projetos
  - http://localhost:8080/netflix-user/swagger-ui.html
  - http://localhost:8081/netflix-suporte/swagger-ui.html
  - http://localhost:8082/netflix-filme/swagger-ui.html

