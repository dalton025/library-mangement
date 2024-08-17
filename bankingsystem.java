import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private double balance;
    private List<Transaction> transactionHistory;

    public User(String username, String password, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    // Method to deposit funds
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid amount for deposit.");
        }
    }

    // Method to withdraw funds
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", -amount));
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount for withdrawal.");
        }
    }

    // Method to transfer funds to another user
    public void transfer(User recipient, double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + recipient.getUsername(), -amount));
            System.out.println("Transfer successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount for transfer.");
        }
    }

    // Method to display transaction history
    public void showTransactionHistory() {
        System.out.println("Transaction History for " + username + ":");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}