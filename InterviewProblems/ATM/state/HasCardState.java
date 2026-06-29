package InterviewProblems.ATM.state;

import java.util.HashMap;

import InterviewProblems.ATM.ATM;
import InterviewProblems.ATM.entity.Card;

public class HasCardState implements ATMState {

    public void insertCard(ATM atm, Card card) {
        System.out.println("Card already inserted");
    }

    public void enterPin(ATM atm, int pin) {
        if (!atm.getCard().validatePIN(pin)) {
            System.out.println("Wrong PIN");
            atm.reset(); // ✅ Reset on failure
            return;
        }

        atm.setAccount(atm.getServer().getAccount(atm.getCard()));
        atm.setState(new AuthenticatedState());

        System.out.println("PIN validated");
    }

    public void withdraw(ATM atm, int amount) {
        System.out.println("Enter PIN first");
    }

    public void deposit(ATM atm, HashMap<Integer, Integer> cash) {
        System.out.println("Enter PIN first");
    }
}