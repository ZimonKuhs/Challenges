package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ATMTest extends TestBase {

    @BeforeEach
    public void setUp() {}

    @AfterEach
    public void tearDown() {}

    /*
     * Constructor tests.
     */

    @Test
    @DisplayName("Invalid operation.")
    public void testOperationError() {
        assertEquals("Error: No such operation exists: operation.\n",
                mainErrorTest(new String[] { "operation", "20", "1000.0" }));
    }

    @Test
    @DisplayName("Invalid transaction amount.")
    public void testAmountError() {
        assertEquals(
                "Errors were found:\nTransaction amount has to be a positive integer: 20.1.\n",
                mainErrorTest(new String[] { "withdraw", "20.1", "1000.0" }));
    }

    @Test
    @DisplayName("Invalid balance amount.")
    public void testBalanceError() {
        assertEquals(
                "Errors were found:\nBalance amount has to be a positive floating point number: -1000.\n",
                mainErrorTest(new String[] { "withdraw", "20", "-1000" }));
    }

    @Test
    @DisplayName("Balance too low.")
    public void testInsufficientBalance() {
        assertEquals("Errors were found:\nInsufficient funds, current balance is 1.0.\n",
                mainErrorTest("withdraw", "5", "1"));
    }

    @Test
    @DisplayName("Above upper withdraw limit.")
    public void testBigWithdraw() {
        assertEquals("Errors were found:\nRequested amount (3000) exceeds withdraw limit of 2000.0.\n",
                mainErrorTest("withdraw", "3000", "20000"));
    }

    @Test
    @DisplayName("Withdraw amount not multiple of 5.")
    public void testAmountNotMultipleOfFive() {
        assertEquals("Errors were found:\nCan only withdraw multiples of 5: 13.\n",
                mainErrorTest("withdraw", "13", "26"));
    }
    
    @Test
    @DisplayName("Standard withdraw.")
    public void testWithdraw() {
        assertEquals("Transaction successful. New balance: 89.5.\n",
                mainOutputTest("withdraw", "30", "120"));
    }

    @Test
    @DisplayName("All three inputs are erroneous.")
    public void testAllInputsWrong() {
        assertEquals("Errors were found:\nTransaction amount has to be a positive integer: 20.5.\n"
                + "Balance amount has to be a positive floating point number: hello.\n",
                mainErrorTest("operation", "20.5", "hello"));
    }
}
