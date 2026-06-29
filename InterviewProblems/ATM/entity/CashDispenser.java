package InterviewProblems.ATM.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import InterviewProblems.ATM.chainOfResponsibility.CashHandler;
import InterviewProblems.ATM.chainOfResponsibility.NoteHandler;

public class CashDispenser {
    private Map<Integer, Integer> notes;
    private CashHandler chain;

    public CashDispenser(HashMap<Integer, Integer> denominations) {
        notes = new HashMap<>(denominations);
        buildChain();
    }

    private void buildChain() {
        List<Integer> denoms = new ArrayList<>(notes.keySet());
        denoms.sort(Collections.reverseOrder());

        CashHandler prev = null;
        chain = null;

        for (int d : denoms) {
            CashHandler handler = new NoteHandler(d);

            if (chain == null) {
                chain = handler;
            }

            if (prev != null) {
                prev.setNext(handler);
            }

            prev = handler;
        }
    }

    public void getNotes() {
        notes.forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println();
    }

    public HashMap<Integer, Integer> withdraw(int amount) {
        if (!canWithdraw(amount)) {
            System.out.println("Not enough cash in ATM");
            return null;
        }

        Map<Integer, Integer> temp = new HashMap<>(notes);
        chain.dispense(amount, temp, true);

        HashMap<Integer, Integer> result = new HashMap<>();
        for (Integer key : notes.keySet()) {
            int diff = notes.get(key) - temp.getOrDefault(key, 0);
            if (diff > 0) {
                result.put(key, diff);
            }
        }

        notes = temp;
        return result;
    }

    public boolean canWithdraw(int amount) {
        int remaining = chain.dispense(amount, notes, false);
        return remaining == 0;
    }

    public void deposit(HashMap<Integer, Integer> denominations) {
        denominations.forEach((k, v) ->
                notes.put(k, notes.getOrDefault(k, 0) + v)
        );
        buildChain();
    }
}
