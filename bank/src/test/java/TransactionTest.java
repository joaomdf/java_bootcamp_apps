import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TransactionTest {
    @Test
    void transactionStoresAllItsValuesAndGetValuesWork() {
        Transaction transaction = new Transaction(500.00,"deposit", LocalDate.of(2019,4,15));
        assertEquals(500.00, transaction.getAmount());
        assertEquals("deposit", transaction.getType());
        assertEquals(LocalDate.of(2019,4,15), transaction.getDate());
        Transaction transaction_two = new Transaction(2500.00,"deposit", LocalDate.of(2020,12,1));
        assertEquals(2500.00, transaction_two.getAmount());
        assertEquals("deposit", transaction_two.getType());
        assertEquals(LocalDate.of(2020,12,1), transaction_two.getDate());
    }

}
