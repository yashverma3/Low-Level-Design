package InterviewProblems.VendingMachine.entity;

import java.util.HashMap;

public class Inventory {
    private final HashMap<Item, Integer> stock;
    private final HashMap<String, Item> codeItemMapping;

    public Inventory() {
        stock = new HashMap<>();
        codeItemMapping = new HashMap<>();
    }

    public void addItem(Item item) {
        int quantity = stock.containsKey(item) ? stock.get(item) : 0;
        stock.put(item, quantity + 1);
        codeItemMapping.put(item.getCode(),item);
    }

    public void removeItem(Item item) {
        if(stock.containsKey(item)) {
            int quantity = stock.get(item);
            if(quantity == 1) {
                stock.remove(item);
                codeItemMapping.remove(item.getCode());
            } else {
                stock.put(item, quantity - 1);
            }
        }
    }

    public boolean isPresent(String code) {
        return codeItemMapping.containsKey(code);
    }

    public HashMap<Item, Integer> getStock() {
        return this.stock;
    }

    public HashMap<String, Item> getCodeItemMapping() {
        return this.codeItemMapping;
    }
}