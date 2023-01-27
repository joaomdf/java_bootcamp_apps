import java.time.LocalDate;
import java.util.ArrayList;
public class BankAccount {
    private final ArrayList<Transaction> transactions;
    private final ArrayList<Double> balance;
    public BankAccount(){
        transactions = new ArrayList<>();
        balance = new ArrayList<>();
    }
    public ArrayList getTransactions(){
        return transactions;
    }

    public ArrayList getBalance(){
        return balance;
    }

    public void deposit(Double money, LocalDate day){
        Transaction new_deposit = new Transaction(money, "deposit", day);
        transactions.add(new_deposit);
        Double total;
        if (balance.size() == 0){
            total = money;
        }
        else {
            total = balance.get(balance.size() - 1) + money;
        }
        balance.add(total);
    }
    public void withdraw(Double money, LocalDate day){
        Transaction new_withdraw = new Transaction(money, "withdraw", day);
        transactions.add(new_withdraw);
        Double total;
        if (balance.size() == 0){
            total = money;
        }
        else {
            total = balance.get(balance.size() - 1) - money;
        }
        balance.add(total);
    }
    public String generateStatement(){
        StringBuilder statement = new StringBuilder();
        String formatted_date;
        statement.append(String.format("|| %-10s || %-10s || %-10s || %-10s||\n", "date", "credit", "debit", "balance"));
        for (int i = transactions.size() - 1; i >= 0; i--) {
            formatted_date = String.format("%02d/%02d/%s", transactions.get(i).getDate().getDayOfMonth(), transactions.get(i).getDate().getMonthValue(), transactions.get(i).getDate().getYear());
            if (transactions.get(i).getType().equals("deposit")) {
                statement.append(String.format("|| %-10s || %-10.2f || %-10s || %-10.2f||\n", formatted_date, transactions.get(i).getAmount(), "-", balance.get(i)));
            }
            else if (transactions.get(i).getType().equals("withdraw")) {
                statement.append(String.format("|| %-10s || %-10s || %-10.2f || %-10.2f||\n", formatted_date, "-", transactions.get(i).getAmount(), balance.get(i)));
            }
        }
        return statement.toString();
    }
}
