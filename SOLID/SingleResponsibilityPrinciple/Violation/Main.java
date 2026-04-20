package SingleResponsibilityPrinciple.Violation;

class Invoice {
    private int amount;

    public Invoice(int amount) {
        this.amount = amount;
    }

    public double calculateTotal() {
        return amount + (amount * 0.1);
    }

    public void printInvoice() {
        System.out.println("Invoice: " + amount);
    }

    public void saveToDB() {
        System.out.println("Saving to DB...");
    }
}

public class Main {
    public static void main(String[] args) {
        Invoice invoice = new Invoice(50);

        double amt = invoice.calculateTotal();
        System.out.println("Total amount: " + amt);

        invoice.printInvoice();
        
        invoice.saveToDB();
    }
}
