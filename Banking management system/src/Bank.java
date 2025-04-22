import java.util.ArrayList;
import java.util.List;

class Bank {
    private final String bankName;
    private final List<Customer> customers;
    private final List<String[]> userCredentials = new ArrayList<>();

    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>();
        userCredentials.add(new String[]{"admin", "password"});
    }

    public String getBankName() { return bankName; }

    public boolean registerUser(String username, String password) {
        for (String[] cred : userCredentials) if (cred[0].equals(username)) return false;
        userCredentials.add(new String[]{username, password});
        return true;
    }

    public Customer authenticateUser(String username, String password) {
        for (String[] cred : userCredentials)
            if (cred[0].equals(username) && cred[1].equals(password))
                return customers.isEmpty() ? null : customers.getFirst();
        return null;
    }

    public Customer createNewCustomer(String name) {
        int id = customers.size() + 1;
        Customer newCustomer = new Customer(id, name);
        customers.add(newCustomer);
        return newCustomer;
    }
}
