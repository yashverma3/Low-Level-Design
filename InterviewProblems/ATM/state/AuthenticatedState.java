package InterviewProblems.ATM.state;

import java.util.HashMap;

import InterviewProblems.ATM.ATM;
import InterviewProblems.ATM.entity.Card;

public class AuthenticatedState implements ATMState {

    public void insertCard(ATM atm, Card card) {
        System.out.println("Session already active");
    }

    public void enterPin(ATM atm, int pin) {
        System.out.println("Already authenticated");
    }

    public void withdraw(ATM atm, int amount) {

        if (!atm.getDispenser().canWithdraw(amount)) {
            System.out.println("ATM has insufficient cash");
            return;
        }

        if (!atm.getAccount().isSufficientBalance(amount)) {
            System.out.println("Insufficient bank balance");
            return;
        }

        atm.getAccount().withdraw(amount);
        HashMap<Integer, Integer> result = atm.getDispenser().withdraw(amount);

        System.out.println("Dispensed cash:");
        result.forEach((k, v) -> System.out.println(k + " x " + v));

        System.out.println("Rs." + amount + " withdrawn successfully");

        atm.reset();
    }

    public void deposit(ATM atm, HashMap<Integer, Integer> cash) {

        int amount = cash.entrySet()
                .stream()
                .mapToInt(e -> e.getKey() * e.getValue())
                .sum();

        atm.getAccount().deposit(amount);
        atm.getDispenser().deposit(cash);

        System.out.println("Rs." + amount + " deposited successfully");

        atm.reset();
    }
}