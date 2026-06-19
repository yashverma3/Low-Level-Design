package InterviewProblems.VendingMachine.state;

import InterviewProblems.VendingMachine.VendingMachine;
import InterviewProblems.VendingMachine.enums.Coin;

public class HasMoneyState extends State {

    public HasMoneyState(VendingMachine machine) {
        super(machine);
    }

    public void insertCoin(Coin coin) {
        System.out.println("Already received full amount");
    }

    public void selectItem(String code) {
        System.out.println("Item already selected.");
    }

    public void dispense(){
        machine.dispenseItem();
        machine.setState(new IdleState(machine));
    }

    public void refund() {
        machine.refund();
        machine.setState(new IdleState(machine));
    }
}
