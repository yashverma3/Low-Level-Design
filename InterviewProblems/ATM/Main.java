package InterviewProblems.ATM;

import java.util.HashMap;
import java.util.Scanner;

import InterviewProblems.ATM.entity.BankAccount;
import InterviewProblems.ATM.entity.BankServer;
import InterviewProblems.ATM.entity.Card;
import InterviewProblems.ATM.entity.CashDispenser;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, Integer> cash = new HashMap<>();
        cash.put(2000, 2);
        cash.put(500, 10);
        cash.put(100, 20);
        
        CashDispenser dispenser = new CashDispenser(cash);
        BankServer server = new BankServer();

        BankAccount acc = new BankAccount();
        acc.deposit(10000);

        Card card = new Card("CARD123", 1234);
        server.addCard(acc, card);

        ATM atm = new ATM(dispenser, server);
        Scanner sc = new Scanner(System.in);

        atm.insertCard(card);

        System.out.print("Enter PIN: ");
        atm.enterPin(sc.nextInt());

        if (!atm.isAuthenticated()) {
            sc.close();
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        atm.withdraw(sc.nextInt());

        // dispenser.getNotes();

        HashMap<Integer, Integer> depositCash = new HashMap<>();
        depositCash.put(500, 2);
        depositCash.put(100, 5);

        atm.insertCard(card);

        System.out.print("Re-enter PIN: ");
        atm.enterPin(sc.nextInt());

        if (!atm.isAuthenticated()) {
            sc.close();
            return;
        }

        atm.deposit(depositCash);

        // dispenser.getNotes();

        sc.close();
    }
}