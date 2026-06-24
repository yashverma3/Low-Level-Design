package InterviewProblems.Splitwise.strategy;

import java.util.HashMap;

import InterviewProblems.Splitwise.entity.Expense;
import InterviewProblems.Splitwise.entity.User;

public class UnequalSplit implements SplitStrategy {
    private String name;
    private User paidBy;
    private double amount;
    private HashMap<User, Double> splits;

    public UnequalSplit(String name, User paidBy, double amount, HashMap<User, Double> splits) {
        this.name = name;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = new HashMap<User, Double>(splits);
    }

    @Override
    public Expense createExpense() {
        double splitTotal = 0.0;
        for (Double splitAmount : splits.values()) {
            splitTotal += splitAmount;
        }
        
        if (splitTotal != amount) {
            System.out.println("Expense cannot be created. Reason: Splits total (" + splitTotal + ") does not match amount (" + amount + ")");
            return null;
        }
        
        return new Expense(name, paidBy, amount, splits);
    }
}
