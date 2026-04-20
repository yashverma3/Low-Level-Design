package OpenClosePrinciple.Valid;

interface PaymentService {
    void pay();
}

class CreditCard implements PaymentService {
    public void pay() {
        System.out.println("Paying via credit card");
    }
}

class UPI implements PaymentService {
    public void pay() {
        System.out.println("Paying via UPI");
    }
}

public class Main{
    public static void main(String[] args) {
        PaymentService ps = new CreditCard();
        ps.pay();
    }
}