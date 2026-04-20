package LiskovSubstitutionPrinciple.Valid;

interface Payment {
    void processPayment();
}

interface Refundable {
    void refund();
}

class CreditCard implements Payment, Refundable {
    @Override
    public void processPayment() {
        System.out.println("Processing Payment...");
    }

    @Override
    public void refund() {
        System.out.println("Refund to Credit Card");
    }
}

class UPI implements Payment {
    @Override
    public void processPayment() {
        System.out.println("Processing Payment...");
    }
}

public class Main {
    public static void main(String[] args) {
        CreditCard payment1 = new CreditCard();
        payment1.refund();

        UPI payment2 = new UPI();
        payment2.processPayment();
    }
}