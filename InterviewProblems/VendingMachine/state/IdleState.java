package InterviewProblems.VendingMachine.state;

import InterviewProblems.VendingMachine.VendingMachine;
import InterviewProblems.VendingMachine.entity.Inventory;
import InterviewProblems.VendingMachine.entity.Item;
import InterviewProblems.VendingMachine.enums.Coin;

public class IdleState extends State {

    public IdleState(VendingMachine machine) {
        super(machine);
    }

    public void insertCoin(Coin coin) {
        System.out.println("Select an item before inserting coin");
    }

    public void selectItem(String code) {
        Inventory inventory = machine.getInventory();
        if(!inventory.isPresent(code)) {
            System.out.println("Selected item is not available");
            return;
        }
        machine.setState(new ItemSelectedState(machine));
        Item item = inventory.getCodeItemMapping().get(code);
        machine.setSelectedItemCode(code);
        System.out.println("Item selected { code: " + item.getCode() + ", name: " + item.getName() + "}");
    }

    public void dispense(){
        System.out.println("No item selected");
    }

    public void refund() {
        System.out.println("No money to refund");
    }
}
