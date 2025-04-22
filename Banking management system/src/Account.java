import java.util.ArrayList;
import java.util.List;

abstract class Account implements AccountOperations {
    private final String accountNumber;
    private final String accountHolderName;
    private final String accountType;
    protected double balance;
    private final List<String> transactions;

    public Account(String accountNumber, String accountHolderName, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() { return accountNumber; }

    protected void addTransaction(String type, double amount) {
        transactions.add(type + ": " + String.format("%.2f", amount));
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Type: " + accountType);
        System.out.println("Current Balance: " + String.format("%.2f", balance));
        System.out.println("Transaction History:");
        for (String transaction : transactions) {
            System.out.println("  " + transaction);
        }
        System.out.println("--------------------");
    }

    @Override
    public abstract void deposit(double amount) throws InvalidAmountException;
    @Override
    public abstract void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException;
}

