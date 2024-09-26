package example.springeventdrivent.stream;

import example.springeventdrivent.domain.Transaction;
import example.springeventdrivent.service.DataSourceService;
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
