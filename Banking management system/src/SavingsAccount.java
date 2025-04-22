class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance, double interestRate) {
        super(accountNumber, accountHolderName, "Savings", balance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        addTransaction("Interest Applied", interest);
        System.out.println("Interest applied. New balance: " + String.format("%.2f", balance));
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Deposit amount must be positive.");
        balance += amount;
        addTransaction("Deposit", amount);
        System.out.println("Deposit successful. New balance: " + String.format("%.2f", balance));
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive.");
        if (balance >= amount) {
            balance -= amount;
            addTransaction("Withdrawal", -amount);
            System.out.println("Withdrawal successful. New balance: " + String.format("%.2f", balance));
        } else throw new InsufficientBalanceException("Insufficient balance.");
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Interest Rate: " + (interestRate * 100) + "%");
    }
}
