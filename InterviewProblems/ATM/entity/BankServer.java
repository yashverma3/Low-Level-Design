package InterviewProblems.ATM.entity;

import java.util.HashMap;
import java.util.Map;

public class BankServer {
    private Map<Card, BankAccount> map = new HashMap<>();

    public void addCard(BankAccount account, Card card) {
        map.put(card, account);
    }

    public boolean validateCard(Card card) {
        return map.containsKey(card);
    }

    public BankAccount getAccount(Card card) {
        return map.get(card);
    }
}