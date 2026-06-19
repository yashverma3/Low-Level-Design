package InterviewProblems.VendingMachine;

import InterviewProblems.VendingMachine.entity.Inventory;
import InterviewProblems.VendingMachine.entity.Item;
import InterviewProblems.VendingMachine.enums.Coin;
import InterviewProblems.VendingMachine.state.IdleState;
import InterviewProblems.VendingMachine.state.State;

public class VendingMachine {
    private Inventory inventory;
    private State state;
    private int balance;
    private String selectedItemCode;

    private static volatile VendingMachine instance;
    private static final Object lock = new Object();

    public static VendingMachine getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new VendingMachine();
                }
            }
        }
        return instance;
    }

    private VendingMachine() {
        balance = 0;
        inventory = new Inventory();
        state = new IdleState(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addItem(String code, String name, int price) {
        Item item = new Item(code, name, price);
        inventory.addItem(item);
    }

    public void selectItem(String code) {
        state.selectItem(code);
    }

    public void insertCoin(Coin coin) {
        state.insertCoin(coin);
    }

    public void dispense() {
        state.dispense();
    }

    public void dispenseItem() {
        Item item = getSelectedItem();
        inventory.removeItem(item);
        balance -= item.getPrice();
        refund();
        selectedItemCode = null;
    }

    public void setSelectedItemCode(String code) {
        this.selectedItemCode = code;
    }

    public void refund() {
        System.out.println("Amount of Rs." + balance + " has been refunded.");
        balance = 0;
    }

    public void addBalance(int amount) {
        balance += amount;
    }

    public Item getSelectedItem() {
        return inventory.getCodeItemMapping().get(selectedItemCode); 
    }

    public int getBalance() {
        return balance;
    }
}
