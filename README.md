Ferramentas utilizadas:
- Gradle
- Spring Boot
- Spring Integration
- Docker
- Docker Compose
- Redis


Aplicações 
    
- hub-processor porta 9000
- hub-api porta 8080
    

Para executar as aplicações: 

    ./gradlew clean build
    docker-compose build
    docker-compose up

Exemplo de chamada do socket: (telnet localhost 9000)

    {"action": "balance","cardnumber":"5353669872708379"}
    {"action": "deposit","cardnumber":"5353669872708379","amount": 100}
    {"action": "withdraw","cardnumber":"5353669872708379","amount": 10000}

Exemplo de chamada da api:
    
    curl http://localhost:8080/balance/5353669872708379
