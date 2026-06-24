package InterviewProblems.Splitwise.entity;

public class User {
    String id;
    String name;
    BalanceSheet balanceSheet;
   
    public User(String id, String name) {
        this.id = id;
        this.name = name;
        balanceSheet = new BalanceSheet(this);
    }

    public BalanceSheet getBalanceSheet() {
        return balanceSheet;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }
}
