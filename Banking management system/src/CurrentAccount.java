class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolderName, double balance, double overdraftLimit) {
        super(accountNumber, accountHolderName, "Current", balance);
        this.overdraftLimit = overdraftLimit;
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
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            addTransaction("Withdrawal", -amount);
            System.out.println("Withdrawal successful. New balance: " + String.format("%.2f", balance));
        } else throw new InsufficientBalanceException("Withdrawal exceeds balance and overdraft limit.");
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Overdraft Limit: " + String.format("%.2f", overdraftLimit));
    }
}
