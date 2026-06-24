package InterviewProblems.Splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import InterviewProblems.Splitwise.entity.User;
import InterviewProblems.Splitwise.entity.Group;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== SPLITWISE TEST CASES ==========\n");
        
        // Get SplitiwiseManager instance
        SplitiwiseManager manager = SplitiwiseManager.getInstance();
        
        // Initialize Users (directly created)
        User user1 = new User("1","Yash");
        User user2 = new User("2","Kaif");
        User user3 = new User("3","Priyam");
        User user4 = new User("4","Mohan");

        // Create Group using manager
        Group group = manager.createGroup("Tredence");
        group.addMember(user1);
        group.addMember(user2);
        group.addMember(user3);
        group.addMember(user4);
        
        System.out.println("------- TEST 1: UNEQUAL SPLIT (Correct Total with Decimals) -------");
        HashMap<User,Double> split1 = new HashMap<>();
        split1.put(user2,75.50);
        split1.put(user3,99.75);
        split1.put(user4,54.75);
        
        manager.createExpense(group, "Juice Pot", user1, 230.0, split1);
       
        System.out.println("\nBalance Sheet After Expense 1:");
        user1.getBalanceSheet().print();
        user2.getBalanceSheet().print();
        user3.getBalanceSheet().print();
        user4.getBalanceSheet().print();
        
        System.out.println("\n------- TEST 2: UNEQUAL SPLIT (Correct Total with Decimals) -------");
        HashMap<User,Double> split2 = new HashMap<>();
        split2.put(user1,125.25);
        split2.put(user3,67.50);
        split2.put(user4,37.25);
        
        manager.createExpense(group, "Goa", user1, 230.0, split2);
       
        System.out.println("\nBalance Sheet After Expense 2:");
        user1.getBalanceSheet().print();
        user2.getBalanceSheet().print();
        user3.getBalanceSheet().print();
        user4.getBalanceSheet().print();
        
        System.out.println("\n------- TEST 3: EQUAL SPLIT (Divide equally) -------");
        ArrayList<User> equalUsers = new ArrayList<>();
        equalUsers.add(user1);
        equalUsers.add(user2);
        equalUsers.add(user3);
        
        manager.createExpense(group, "Dinner", user4, 500.75, equalUsers);
       
        System.out.println("\nBalance Sheet After Expense 3 (Equal Split):");
        user1.getBalanceSheet().print();
        user2.getBalanceSheet().print();
        user3.getBalanceSheet().print();
        user4.getBalanceSheet().print();
        
        System.out.println("\n------- TEST 4: UNEQUAL SPLIT (Incorrect Total) -------");
        HashMap<User,Double> invalidSplit = new HashMap<>();
        invalidSplit.put(user1,50.50);
        invalidSplit.put(user2,49.25);
        // Total is 99.75 but amount is 200.0 - should fail
        manager.createExpense(group, "Invalid Expense", user3, 200.0, invalidSplit);
        System.out.println();
        
        System.out.println("\n------- TEST 5: SETTLEMENT OF BALANCES -------");
        System.out.println("Before settlement:");
        user1.getBalanceSheet().print();
        user2.getBalanceSheet().print();
        user3.getBalanceSheet().print();
        user4.getBalanceSheet().print();
        
        // user2 pays user1
        System.out.println("\nuser2 pays Rs.99.50 to user1:");
        manager.settleBalance(group, user2, user1, 99.50);
        
        // user3 pays user1
        System.out.println("\nuser3 pays Rs.167.25 to user1:");
        manager.settleBalance(group, user3, user1, 167.25);
        
        System.out.println("\nBalance Sheet After Settlement:");
        user1.getBalanceSheet().print();
        user2.getBalanceSheet().print();
        user3.getBalanceSheet().print();
        user4.getBalanceSheet().print();
        
        System.out.println("\n------- TEST 6: UNEQUAL SPLIT (Decimal Values) -------");
        HashMap<User,Double> split3 = new HashMap<>();
        split3.put(user2,88.33);
        split3.put(user4,61.67);
        
        manager.createExpense(group, "Movie", user3, 150.0, split3);
        
        System.out.println("\nBalance Sheet After Expense 4:");
        user1.getBalanceSheet().print();
        user2.getBalanceSheet().print();
        user3.getBalanceSheet().print();
        user4.getBalanceSheet().print();
        
        System.out.println("\n------- TEST 7: SETTLEMENT WITH DECIMAL AMOUNTS -------");
        System.out.println("user4 pays Rs.123.76 to user1:");
        manager.settleBalance(group, user4, user1, 123.76);
        
        System.out.println("\nFinal Balance Sheet After Test 7:");
        user1.getBalanceSheet().print();
        user2.getBalanceSheet().print();
        user3.getBalanceSheet().print();
        user4.getBalanceSheet().print();
        
        System.out.println("\n------- TEST 8: COMPLEX UNEQUAL SPLIT (With Decimals) -------");
        ArrayList<User> otherUsers = new ArrayList<>();
        otherUsers.add(user1);
        otherUsers.add(user2);
        
        HashMap<User,Double> split4 = new HashMap<>();
        split4.put(user1,199.99);
        split4.put(user2,200.01);
        
        manager.createExpense(group, "Lunch", user3, 400.0, split4);
        
        System.out.println("\nBalance Sheet After Expense 5:");
        user1.getBalanceSheet().print();
        user2.getBalanceSheet().print();
        user3.getBalanceSheet().print();
        user4.getBalanceSheet().print();
        
        System.out.println("\n------- TEST 9: FINAL MULTIPLE SETTLEMENTS -------");
        System.out.println("user1 pays Rs.50.25 to user3:");
        manager.settleBalance(group, user1, user3, 50.25);
        
        System.out.println("\nuser2 pays Rs.75.99 to user4:");
        manager.settleBalance(group, user2, user4, 75.99);
        
        System.out.println("\nFinal Balance Sheet:");
        user1.getBalanceSheet().print();
        user2.getBalanceSheet().print();
        user3.getBalanceSheet().print();
        user4.getBalanceSheet().print();
        
        System.out.println("\n========== ALL TEST CASES COMPLETED ==========");
    }
}