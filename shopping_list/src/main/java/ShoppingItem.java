public class ShoppingItem {
    public Double price;
    public String name;

    public ShoppingItem(String name, Double price){
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public Double getPrice(){
        return price;
    }
}
