package example.springeventdrivent.controller;

import example.springeventdrivent.domain.Transaction;
import example.springeventdrivent.ondemand.CashCardTransactionOnDemand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashCardController {

    private final CashCardTransactionOnDemand cashCardTransactionOnDemand;

    public CashCardController(CashCardTransactionOnDemand cashCardTransactionOnDemand) {
        this.cashCardTransactionOnDemand = cashCardTransactionOnDemand;
    }

    @PostMapping(path = "/publish/txn")
    public void publishTxn(@RequestBody Transaction transaction) {
        System.out.println("POST for Transaction: " + transaction);
        this.cashCardTransactionOnDemand.publishOnDemand(transaction);
    }
}
