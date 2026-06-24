package InterviewProblems.Splitwise;

import java.util.ArrayList;
import java.util.HashMap;

import InterviewProblems.Splitwise.entity.Expense;
import InterviewProblems.Splitwise.entity.Group;
import InterviewProblems.Splitwise.entity.User;
import InterviewProblems.Splitwise.strategy.EqualSplit;
import InterviewProblems.Splitwise.strategy.SplitStrategy;
import InterviewProblems.Splitwise.strategy.UnequalSplit;

public class SplitiwiseManager {
    ArrayList<Group> groups;
    private static volatile SplitiwiseManager instance;
    private static final Object lock = new Object();

    public static SplitiwiseManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SplitiwiseManager();
                }
            }
        }
        return instance;
    }

    private SplitiwiseManager() {
        groups = new ArrayList<>();
    }

    public Group createGroup(String name) {
        Group group = new Group(name);
        groups.add(group);
        return group;
    }

    public Group createGroup(String name, ArrayList<User> members) {
        Group group = new Group(name, members);
        groups.add(group);
        return group;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void createExpense(Group group, String name, User paidBy, double amount, ArrayList<User> users) {
        SplitStrategy strategy = new EqualSplit(name, paidBy, amount, users);
        Expense expense = strategy.createExpense();
        if (expense != null) {
            group.addExpense(expense);
        }
    }

    public void createExpense(Group group, String name, User paidBy, double amount, HashMap<User, Double> splits) {
        SplitStrategy strategy = new UnequalSplit(name, paidBy, amount, splits);
        Expense expense = strategy.createExpense();
        if (expense != null) {
            group.addExpense(expense);
        }
    }

    public void settleBalance(Group group, User paidBy, User paidTo, double amount) {
        group.settleBalance(paidBy, paidTo, amount);
    }
}
