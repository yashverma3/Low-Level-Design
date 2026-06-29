package InterviewProblems.ATM.state;

import java.util.HashMap;

import InterviewProblems.ATM.ATM;
import InterviewProblems.ATM.entity.Card;

public interface ATMState {
    void insertCard(ATM atm, Card card);
    void enterPin(ATM atm, int pin);
    void withdraw(ATM atm, int amount);
    void deposit(ATM atm, HashMap<Integer, Integer> cash);
}
