import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ShoppingItemTest {
    @Test
    void recordsPriceAndName(){
        ShoppingItem donuts = new ShoppingItem("donuts", 2.00);
        assertEquals("donuts", donuts.name);
        assertEquals(2.00,donuts.price);
    }
}
