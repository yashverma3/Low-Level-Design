package InterviewProblems.ATM.chainOfResponsibility;

import java.util.Map;

public abstract class CashHandler {
    protected CashHandler next;
    protected int denomination;

    public CashHandler(int denomination) {
        this.denomination = denomination;
    }

    public void setNext(CashHandler next) {
        this.next = next;
    }

    public abstract int dispense(int amount, Map<Integer, Integer> notes, boolean deduct);
}