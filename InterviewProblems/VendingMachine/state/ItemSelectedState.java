package InterviewProblems.VendingMachine.state;

import InterviewProblems.VendingMachine.VendingMachine;
import InterviewProblems.VendingMachine.entity.Item;
import InterviewProblems.VendingMachine.enums.Coin;

public class ItemSelectedState extends State {

    public ItemSelectedState(VendingMachine machine) {
        super(machine);
    }

    public void insertCoin(Coin coin) {
        machine.addBalance(coin.getValue());
        Item item = machine.getSelectedItem();
        int balance = machine.getBalance();
        if(balance >= item.getPrice()) {
            System.out.println("Sufficient amount");
            machine.setState(new HasMoneyState(machine));
        } else {
            System.out.println("Insufficient amount");
        }
    }

    public void selectItem(String code) {
        System.out.println("Item is already selected");
    }

    public void dispense(){
        System.out.println("No coin inserted");
    }

    public void refund() {
        System.out.println("No money to refund");
    }
}
