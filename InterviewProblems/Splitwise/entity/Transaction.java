package InterviewProblems.Splitwise.entity;

public class Transaction {
    User from;
    User to;
    double amount;
    boolean isPaid;

    public Transaction(User from, User to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        isPaid = false;
    }
    
    public double getAmount() {
        return amount;
    }

    public User getFromUser() {
        return from;
    }

    public User getToUser() {
        return to;
    }

    public boolean isSettled() {
        return isPaid;
    }
}
