import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<ShoppingItem> list;

    public ShoppingList(){
        list = new ArrayList<>();
    }
    public void addItems(ShoppingItem item){
        list.add(item);
    }
    public ArrayList<ShoppingItem> getItems(){
        return list;
    }
}
