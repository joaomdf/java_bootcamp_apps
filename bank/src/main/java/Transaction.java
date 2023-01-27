import java.time.LocalDate;
public class Transaction {
    private final Double amount;
    private final String type;
    private final LocalDate date;
    public Transaction(Double amount, String type, LocalDate date) {
        this.amount = amount;
        this.type = type;
        this.date = date;
    }
    public Double getAmount(){
        return amount;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getType(){
        return type;
    }
}