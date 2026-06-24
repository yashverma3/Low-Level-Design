package InterviewProblems.Splitwise.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Group {
    int transactionId = 0;
    String name;
    ArrayList<User> members;
    ArrayList<Expense> expenses;
    ArrayList<Transaction> transactions;

    public Group(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public Group(String name, ArrayList<User> members) {
        this.name = name;
        this.members = new ArrayList<>(members);
        this.expenses = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addMember(User user) {
        if (members.contains(user)) {
            System.out.println("User " + user.getName() + " is already a member");
            return;
        }
        members.add(user);
    }

    public void addExpense(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be created");
        }
        expenses.add(expense);
        HashMap<User,Double> splits = expense.getSplits();
        User paidBy = expense.getPaidByUser();
       
        for(User user: splits.keySet()) {
            Transaction owe = new Transaction(user, paidBy, splits.get(user));
            paidBy.getBalanceSheet().addTransaction(owe);
            user.getBalanceSheet().addTransaction(owe);
            transactions.add(owe);
        }
       
        System.out.println(expense.getName() + " expense added.");
    }

    public void settleBalance(User user1, User user2, double amount) {
        if (user1 == user2) {
            throw new IllegalArgumentException("Both users are same");
        }
        // user1 paid to user2 amount
        user1.getBalanceSheet().settleBalance(user2, amount);
        user2.getBalanceSheet().settleBalance(user1, amount);
    }
}
