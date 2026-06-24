package InterviewProblems.Splitwise.entity;

import java.util.HashMap;

public class BalanceSheet {
    User user;
    double dueAmount;
    HashMap<User,Double> pending;

    public BalanceSheet(User user) {
        this.user = user;
        dueAmount = 0.0;
        pending = new HashMap<>();
    }

    public void addTransaction(Transaction transaction) {
        User from = transaction.getFromUser();
        User to = transaction.getToUser();
        double amount = transaction.getAmount();

        if(from == to) return;

        if (user == to) {
            dueAmount += amount;
            pending.put(from, pending.getOrDefault(from,0.0) + amount);
        }
        else {
            dueAmount -= amount;
            pending.put(to, pending.getOrDefault(to,0.0) - amount);
        }
    }

    public void settleBalance(User relatedUser, double amount) {
        double currentPending = pending.getOrDefault(relatedUser, 0.0);
        
        if (currentPending >= 0) {
            // relatedUser owes us, they are paying us
            pending.put(relatedUser, currentPending - amount);
            dueAmount -= amount;
            System.out.println(relatedUser.getName() + " paid Rs." + amount + " to " + user.getName());
        } else {
            // We owe relatedUser, we are paying them
            pending.put(relatedUser, currentPending + amount);
            dueAmount += amount;
        }
        
    }

    private String getStatus() {
        if(dueAmount == 0) return user.name + " is all settled up!";
        if(dueAmount > 0) return user.name + " lent Rs." + dueAmount;
        return user.name + " owes Rs." + (-dueAmount);
    }

    public void print() {
        System.out.println(getStatus());
        for(User relatedUser: pending.keySet()) {
            if(user == relatedUser) continue;
            double amount = pending.get(relatedUser);
            if(amount == 0) continue;
            if(amount > 0) System.out.println(user.getName() + " lent Rs." + amount + " to " + relatedUser.getName());
            else System.out.println(user.getName() + " owes Rs." + (-amount) + " to " + relatedUser.getName());
        }
    }
}
