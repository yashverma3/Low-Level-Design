package SingleResponsibilityPrinciple.Valid;

class Invoice {
    private int amount;

    public Invoice(int amount) {
        this.amount = amount;
    }

    public double calculateTotal() {
        return amount + (amount * 0.1);
    }

    public int getAmount() {
        return amount;
    }
}

class PrintInvoice {
    public void print(Invoice invoice) {
        System.out.println("Invoice: " + invoice.getAmount());
    }
}

class SaveToDB {
    public void save(Invoice invoice) {
        System.out.println("Saving to DB...");
    }
}

public class Main {
    public static void main(String[] args) {
        Invoice invoice = new Invoice(50);
        System.out.println("Total Amount: " + invoice.calculateTotal());

        PrintInvoice printInvoice = new PrintInvoice();
        printInvoice.print(invoice);

        SaveToDB saveToDB = new SaveToDB();
        saveToDB.save(invoice);
    }
}