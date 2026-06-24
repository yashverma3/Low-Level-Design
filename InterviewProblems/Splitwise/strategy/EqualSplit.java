package InterviewProblems.Splitwise.strategy;

import java.util.ArrayList;
import java.util.HashMap;

import InterviewProblems.Splitwise.entity.Expense;
import InterviewProblems.Splitwise.entity.User;

public class EqualSplit implements SplitStrategy {
    private String name;
    private User paidBy;
    private double amount;
    private ArrayList<User> users;

    public EqualSplit(String name, User paidBy, double amount, ArrayList<User> users) {
        this.name = name;
        this.paidBy = paidBy;
        this.amount = amount;
        this.users = new ArrayList<>(users);
    }

    @Override
    public Expense createExpense() {
        // Divide amount equally among all users
        double splitAmount = amount / users.size();
        
        HashMap<User, Double> splits = new HashMap<>();
        for (User user : users) {
            splits.put(user, splitAmount);
        }
        
        return new Expense(name, paidBy, amount, splits);
    }
}
