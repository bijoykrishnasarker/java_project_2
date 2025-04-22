public interface AccountOperations {
    void deposit(double amount) throws InvalidAmountException;
    void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException;
    void displayAccountInfo();
}
