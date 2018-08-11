package br.com.hub.api.resources;

import br.com.hub.core.transformers.TransactionService;
import br.com.hub.domain.TransactionResponse;
import br.com.hub.domain.builders.TransactionBuilder;
import br.com.hub.domain.exceptions.CardNotFound;
import br.com.hub.domain.types.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static br.com.hub.domain.types.TransactionType.BALANCE;
import static br.com.hub.domain.types.TransactionType.DEPOSIT;
import static br.com.hub.domain.types.TransactionType.WITHDRAW;

/**
 * Created by STELO\renato.5a on 8/11/18.
 */
@Controller
@RequestMapping()
public class CardResource {

    @Autowired
    private TransactionService service;

    @ResponseBody
    @GetMapping("/balance/{card}")
    public TransactionResponse balance(@PathVariable("card") String card) {
        return service.apply(TransactionBuilder.aTransaction()
                .withAction(BALANCE)
                .withCardnumber(card).build());
    }

    @PostMapping("/withdraw/{card}")
    @ResponseBody
    public TransactionResponse withdraw(@PathVariable("card") String card, @RequestParam("amount") Double amount) {
        return service.apply(TransactionBuilder.aTransaction()
                .withAction(WITHDRAW)
                .withCardnumber(card)
                .withAmount(amount).build());
    }

    @PutMapping("/deposit/{card}")
    @ResponseBody
    public TransactionResponse deposit(@PathVariable("card") String card, @RequestParam("amount") Double amount) {
        return service.apply(TransactionBuilder.aTransaction()
                .withAction(DEPOSIT)
                .withCardnumber(card)
                .withAmount(amount).build());
    }

    @CrossOrigin
    @ResponseBody
    @ExceptionHandler(CardNotFound.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void notFoundHandler(CardNotFound ex) {
    }

}
