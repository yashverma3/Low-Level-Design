package InterviewProblems.ATM;

import InterviewProblems.ATM.state.ATMState;
import InterviewProblems.ATM.state.IdleState;
import InterviewProblems.ATM.entity.BankServer;
import InterviewProblems.ATM.entity.CashDispenser;
import InterviewProblems.ATM.entity.Card;

import java.util.HashMap;

import InterviewProblems.ATM.entity.BankAccount;

public class ATM {

    private ATMState state;
    private CashDispenser dispenser;
    private BankServer server;

    private Card card;
    private BankAccount account;

    public ATM(CashDispenser dispenser, BankServer server) {
        this.dispenser = dispenser;
        this.server = server;
        this.state = new IdleState();
    }

    public void insertCard(Card card) {
        state.insertCard(this, card);
    }

    public void enterPin(int pin) {
        state.enterPin(this, pin);
    }

    public void withdraw(int amount) {
        state.withdraw(this, amount);
    }

    public void deposit(HashMap<Integer, Integer> cash) {
        state.deposit(this, cash);
    }

    public boolean isAuthenticated() {
        return account != null;
    }

    public void setState(ATMState state) { this.state = state; }
    public CashDispenser getDispenser() { return dispenser; }
    public BankServer getServer() { return server; }

    public void setCard(Card card) { this.card = card; }
    public Card getCard() { return card; }

    public void setAccount(BankAccount acc) { this.account = acc; }
    public BankAccount getAccount() { return account; }

    public void reset() {
        card = null;
        account = null;
        state = new IdleState();
        System.out.println("Session ended. Remove card\n");
    }
}