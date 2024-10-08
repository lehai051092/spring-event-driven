package example.cashcard.stream;

import example.cashcard.domain.Transaction;
import example.cashcard.service.DataSourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class CashCardTransactionStream {

    @Bean
    public Supplier<Transaction> approvalRequest(DataSourceService dataSource) {
        return dataSource::getData;
    }

    @Bean
    public DataSourceService dataSourceService() {
        return new DataSourceService();
    }
}
