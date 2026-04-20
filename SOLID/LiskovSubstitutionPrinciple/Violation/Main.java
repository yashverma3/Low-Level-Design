package LiskovSubstitutionPrinciple.Violation;

class Payment {
    public void processPayment() {
        System.out.println("Processing Payment...");
    }
    
    public void refund() {
        System.out.println("Refund Processed");
    }
}

class CreditCard extends Payment {
    @Override
    public void refund() {
        System.out.println("Refund to Credit Card");
    }
}

class UPI extends Payment {
    @Override
    public void refund() {
        throw new UnsupportedOperationException("UPI Refund not supported");
    }
}

public class Main {
    public static void main(String[] args) {
        Payment payment1 = new CreditCard();
        payment1.refund();

        Payment payment2 = new UPI();
        payment2.refund(); // throws an error
    }
}