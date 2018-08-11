package br.com.hub.api.config;

import br.com.hub.core.repositories.CardRepository;
import br.com.hub.core.transformers.TransactionService;
import br.com.hub.domain.builders.TransactionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import javax.annotation.PostConstruct;

import static br.com.hub.domain.types.TransactionType.DEPOSIT;

@EnableAutoConfiguration
@ComponentScan("br.com.hub")
@EnableRedisRepositories("br.com.hub")
@SpringBootApplication
public class Application {

    @Autowired
    private TransactionService service;
    @Autowired
    private CardRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void buildDataBase() {
        repository.deleteAll();
        service.apply(TransactionBuilder.aTransaction().withAction(DEPOSIT).withCardnumber("5418973351426930").withAmount(10000D).build());
        service.apply(TransactionBuilder.aTransaction().withAction(DEPOSIT).withCardnumber("5353669872708379").withAmount(10000D).build());
        service.apply(TransactionBuilder.aTransaction().withAction(DEPOSIT).withCardnumber("5205050805006365").withAmount(10000D).build());
    }
}