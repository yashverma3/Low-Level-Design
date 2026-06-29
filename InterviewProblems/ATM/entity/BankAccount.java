package InterviewProblems.ATM.entity;

public class BankAccount {
    private int balance;

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int fetchBalance() {
        return balance;
    }

    public boolean isSufficientBalance(int amount) {
        return amount <= balance;
    }
}
