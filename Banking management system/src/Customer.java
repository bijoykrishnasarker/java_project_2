import java.util.ArrayList;
import java.util.List;

class Customer {
    private final int customerId;
    private final String name;
    private final List<Account> accounts;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }

    public void addAccount(Account account) { accounts.add(account); }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) if (acc.getAccountNumber().equals(accountNumber)) return acc;
        return null;
    }

    public void displayCustomerInfo() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Accounts:");
        if (accounts.isEmpty()) System.out.println("No accounts associated.");
        else for (Account acc : accounts) acc.displayAccountInfo();
        System.out.println("--------------------");
    }
}