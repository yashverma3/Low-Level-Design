package InterviewProblems.Splitwise.entity;

import java.util.HashMap;

public class Expense {
    String name;
    User paidBy;
    double amount;
    HashMap<User,Double> splits;

    public Expense(String name, User paidBy, double amount, HashMap<User,Double> splits) {
        this.name = name;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = new HashMap<>(splits);
    }

    public HashMap<User,Double> getSplits() {
        return splits;
    }

    public User getPaidByUser() {
        return paidBy;
    }
   
    public String getName() {
        return name;
    }
}

