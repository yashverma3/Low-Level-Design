package InterviewProblems.ATM.state;

import java.util.HashMap;

import InterviewProblems.ATM.ATM;
import InterviewProblems.ATM.entity.Card;

public class IdleState implements ATMState {

    public void insertCard(ATM atm, Card card) {
        if (!atm.getServer().validateCard(card)) {
            System.out.println("Invalid card");
            return;
        }
        atm.setCard(card);
        atm.setState(new HasCardState());
        System.out.println("Card inserted");
    }

    public void enterPin(ATM atm, int pin) {
        System.out.println("Insert card first");
    }

    public void withdraw(ATM atm, int amount) {
        System.out.println("Insert card first");
    }

    public void deposit(ATM atm, HashMap<Integer, Integer> cash) {
        System.out.println("Insert card first");
    }
}