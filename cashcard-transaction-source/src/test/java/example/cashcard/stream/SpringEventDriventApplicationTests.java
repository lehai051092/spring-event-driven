package example.cashcard.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.cashcard.domain.CashCard;
import example.cashcard.domain.Transaction;
import example.cashcard.service.DataSourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class SpringEventDriventApplicationTests {

    @MockBean
    private DataSourceService dataSourceService;

    @Test
    void basicCashCardSupplier1(@Autowired OutputDestination outputDestination) throws IOException {
        // Configure the mocked DataSourceService
        Transaction testTransaction = new Transaction(1L, new CashCard(123L, "sarah1", 1.00));
        given(dataSourceService.getData()).willReturn(testTransaction);

        // invoke the outputDestination and make sure it returned something
        Message<byte[]> result = outputDestination.receive(5000, "approvalRequest-out-0");
        assertThat(result).isNotNull();

        // Deserialize the transaction and inspect it
        ObjectMapper objectMapper = new ObjectMapper();
        Transaction transaction = objectMapper.readValue(result.getPayload(), Transaction.class);

        assertThat(transaction.id()).isEqualTo(1L);
        assertThat(transaction.cashCard()).isEqualTo(testTransaction.cashCard());
    }
}
