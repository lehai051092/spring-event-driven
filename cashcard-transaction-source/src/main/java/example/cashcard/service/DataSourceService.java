package example.cashcard.service;

import example.cashcard.domain.CashCard;
import example.cashcard.domain.Transaction;

import java.util.Random;

public class DataSourceService {

    public Transaction getData() {
        CashCard cashCard = new CashCard(
                new Random().nextLong(), // Random ID
                "sarah1",
                new Random().nextDouble(100.00) // Random Amount
        );
        Transaction transaction = new Transaction(new Random().nextLong(), cashCard);
        System.out.println("Generating Transaction: " + transaction);
        return transaction;
    }
}
