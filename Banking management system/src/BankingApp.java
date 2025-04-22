import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingApp {
    private static final Bank myBank = new Bank("Simple Bank");
    private static final Scanner scanner = new Scanner(System.in);
    private static Customer loggedInCustomer = null;

    public static void main(String[] args) {
        Customer customer1 = myBank.createNewCustomer("Test User");
        customer1.addAccount(new SavingsAccount("SA001", "Test User", 100.00, 0.02));
        showMainMenu();
    }
    public static int getInputInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
    }
    public static double getInputDouble() {
        // while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }

    public static void showMainMenu() {
        while (true) {
            System.out.println("\n----- " + myBank.getBankName() + " -----");
            System.out.println("1. Login\n2. Sign Up\n0. Exit\nEnter your choice: ");
            int choice = getInputInt();
            if (choice == 1) {
                login();
                if (loggedInCustomer != null) {
                    showCustomerMenu();
                    loggedInCustomer = null;
                }
            } else if (choice == 2) {
                signUp();
            } else if (choice == 0) {
                System.out.println("Thanks for using " + myBank.getBankName());
                return;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void login() {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        loggedInCustomer = myBank.authenticateUser (username, password);
        System.out.println(loggedInCustomer != null ? "Login successful! Welcome, " + loggedInCustomer.getName() + "." : "Login failed. Invalid credentials.");
    }

    public static void signUp() {
        System.out.print("New Username: ");
        String username = scanner.next();
        System.out.print("New Password: ");
        String password = scanner.next();
        if (myBank.registerUser (username, password)) {
            System.out.print("Your Name: ");
            String name = scanner.next();
            Customer newCustomer = myBank.createNewCustomer(name);
            System.out.println("New customer created with ID: " + newCustomer.getCustomerId() + ". Now log in.");
        } else {
            System.out.println("Username already exists. Choose another.");
        }
    }

    public static void showCustomerMenu() {
        if (loggedInCustomer == null) return;
        while (true) {
            System.out.println("\n--- Customer Menu (" + loggedInCustomer.getName() + ") ---");
            System.out.println("1. View Accounts\n2. Deposit\n3. Withdraw\n0. Logout\nChoice: ");
            int choice = getInputInt();
            if (choice == 1) {
                loggedInCustomer.displayCustomerInfo();
            } else if (choice == 2) {
                performDeposit();
            } else if (choice == 3) {
                performWithdrawal();
            } else if (choice == 0) {
                return; // Log out
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void performDeposit() {
        System.out.print("Account Number: ");
        String accountNumber = scanner.next();
        Account account = loggedInCustomer.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Deposit Amount: ");
            double amount = getInputDouble();
            if (amount > 0) {
                try {
                    account.deposit(amount);
                    System.out.println("Deposit successful!");
                } catch (InvalidAmountException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid deposit amount.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public static void performWithdrawal() {
        System.out.print("Account Number: ");
        String accountNumber = scanner.next();
        Account account = loggedInCustomer.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Withdrawal Amount: ");
            double amount = getInputDouble();
            if (amount > 0) {
                try {
                    account.withdraw(amount);
                    System.out.println("Withdrawal successful!");
                } catch (InsufficientBalanceException | InvalidAmountException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid withdrawal amount.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
}