package example.cashcard.enricher;

import example.cashcard.domain.EnrichedTransaction;
import example.cashcard.domain.Transaction;
import example.cashcard.service.EnrichmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class CashCardTransactionEnricher {

    @Bean
    EnrichmentService enrichmentService() {
        return new EnrichmentService();
    }

    @Bean
    public Function<Transaction, EnrichedTransaction> enrichTransaction(EnrichmentService enrichmentService) {
        return enrichmentService::enrichTransaction;
    }
}
