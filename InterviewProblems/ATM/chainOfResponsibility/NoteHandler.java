package InterviewProblems.ATM.chainOfResponsibility;

import java.util.Map;

public class NoteHandler extends CashHandler {
    public NoteHandler(int denomination) {
        super(denomination);
    }

    public int dispense(int amount, Map<Integer, Integer> notes, boolean deduct) {

        int available = notes.getOrDefault(denomination, 0);
        int required = amount / denomination;

        int used = Math.min(required, available);

        if (deduct && used > 0) {
            notes.put(denomination, available - used);
        }

        amount -= used * denomination;

        if (amount > 0 && next != null) {
            return next.dispense(amount, notes, deduct);
        }

        return amount;
    }
}