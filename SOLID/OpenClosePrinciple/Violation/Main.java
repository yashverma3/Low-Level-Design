package OpenClosePrinciple.Violation;

class PaymentService {
    public void pay(String type) {
        if (type == "CREDIT_CARD") {
            System.out.println("Paying via credit card");
        } else if (type == "UPI") {
            System.out.println("Paying via UPI");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentService ps = new PaymentService();
        ps.pay("UPI");
    }
}