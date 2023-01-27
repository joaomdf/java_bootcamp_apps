import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
public class ShoppingListTest {
    @Test
    void checkEmptyShoppingListCreated(){
        ShoppingList shopping_list = new ShoppingList();
        assertEquals(true, shopping_list.getItems().isEmpty());
    }
//
//    @Test
//    void checkAddItemDDonut(){
//        ShoppingList shopping_list = new ShoppingList();
//        shopping_list.addItems("donuts");
//        ArrayList<String> mock_list = new ArrayList<>();
//        mock_list.add("donuts");
//        assertEquals(mock_list,shopping_list.getItems());
//    }
//    @Test
//    void checkAddItemCookies(){
//        ShoppingList shopping_list = new ShoppingList();
//        shopping_list.addItems("cookies");
//        ArrayList<String> mock_list = new ArrayList<>();
//        mock_list.add("cookies");
//        assertEquals(mock_list,shopping_list.getItems());
//    }
//
//    @Test
//    void checkAddSeveralItems(){
//        ShoppingList shopping_list = new ShoppingList();
//        shopping_list.addItems("cookies");
//        shopping_list.addItems("donuts");
//        ArrayList<String> mock_list = new ArrayList<>();
//        mock_list.add("cookies");
//        mock_list.add("donuts");
//        assertEquals(mock_list,shopping_list.getItems());
//    }

    @Test
    public void checkAddItemDonut() {
        ShoppingItem mocked_donuts = mock(ShoppingItem.class);
        when(mocked_donuts.getPrice()).thenReturn(2.00);
        when(mocked_donuts.getName()).thenReturn("donuts");
        ShoppingList test_list = new ShoppingList();
        test_list.addItems(mocked_donuts);
        assertTrue(test_list.getItems().contains(mocked_donuts));
    }
    @Test
    public void checkAddItemCookie() {
        ShoppingItem mocked_cookies = mock(ShoppingItem.class);
        ShoppingList test_list = new ShoppingList();
        test_list.addItems(mocked_cookies);
        assertTrue(test_list.getItems().contains(mocked_cookies));
    }

    @Test
    public void checkAddSeveralItems() {
        ShoppingItem mocked_cookies = mock(ShoppingItem.class);
        ShoppingItem mocked_donuts = mock(ShoppingItem.class);
        ShoppingList test_list = new ShoppingList();
        test_list.addItems(mocked_cookies);
        test_list.addItems(mocked_donuts);
        assertTrue(test_list.getItems().contains(mocked_cookies));
        assertTrue(test_list.getItems().contains(mocked_donuts));
    }
}
