import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assert
public class BankAccountTest {
    @Test
    void createsAndUpdatesListOfTransactions(){
        BankAccount bank_account = new BankAccount();
        assertTrue(bank_account.getTransactions().isEmpty());
        bank_account.deposit(300.00,LocalDate.of(2012,3,4));
        ArrayList<Transaction> test_one = new ArrayList<>();
        test_one.add(new_test_deposit);
        assertEquals(test_one, bank_account.getTransactions());
    }

    @Test
    void createsAndUpdatesListOfBalanceRecords(){
        BankAccount bank_account = new BankAccount();
        assertTrue(bank_account.getBalance().isEmpty());
        ArrayList<Double> test_one = new ArrayList<>();
        test_one.add(300.00);
        bank_account.deposit(300.00,LocalDate.of(2012,3,4));
        assertThat(actual).isEqualToComparingFieldByField(expected);
        assertEquals(test_one, bank_account.getBalance());
    }
    @Test
    void generateAccurateStatementForDeposit(){

    }

    @Test
    void generateAccurateStatementForWithdrawal(){

    }
}
