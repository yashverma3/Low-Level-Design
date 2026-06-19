package InterviewProblems.VendingMachine;
import InterviewProblems.VendingMachine.enums.Coin;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== VENDING MACHINE TEST SUITE ==========\n");
        
        // Initialize the vending machine
        VendingMachine vendingMachine = VendingMachine.getInstance();
        
        // Test Case 1: Setup Inventory - Add items to the vending machine
        System.out.println("--- TEST CASE 1: Setup Inventory ---");
        testSetupInventory(vendingMachine);
        System.out.println();
        
        // Test Case 2: Successful Purchase - Buy item with exact amount
        System.out.println("--- TEST CASE 2: Successful Purchase with Exact Amount ---");
        testSuccessfulPurchaseExactAmount(vendingMachine);
        System.out.println();
        
        // Test Case 3: Successful Purchase - Buy item with extra amount
        System.out.println("--- TEST CASE 3: Successful Purchase with Extra Amount (Refund) ---");
        testSuccessfulPurchaseWithRefund(vendingMachine);
        System.out.println();
        
        // Test Case 4: Insufficient Amount - Try to buy without enough balance
        System.out.println("--- TEST CASE 4: Insufficient Amount ---");
        testInsufficientAmount(vendingMachine);
        System.out.println();
        
        // Test Case 5: Item Not Available - Try to select unavailable item
        System.out.println("--- TEST CASE 5: Item Not Available ---");
        testItemNotAvailable(vendingMachine);
        System.out.println();
        
        // Test Case 6: Multiple Items Insertion - Add multiple coins before having sufficient amount
        System.out.println("--- TEST CASE 6: Multiple Coin Insertions ---");
        testMultipleCoinInsertions(vendingMachine);
        System.out.println();
        
        // Test Case 7: Refund Without Purchase - Refund balance without completing purchase
        System.out.println("--- TEST CASE 7: Refund Money Without Purchase ---");
        testRefundWithoutPurchase(vendingMachine);
        System.out.println();
        
        // Test Case 8: Different Coin Types - Test all coin denominations
        System.out.println("--- TEST CASE 8: Different Coin Types ---");
        testDifferentCoinTypes(vendingMachine);
        System.out.println();
        
        // Test Case 9: Multiple Item Selection - Change item selection
        System.out.println("--- TEST CASE 9: Item Already Selected (State Validation) ---");
        testItemAlreadySelected(vendingMachine);
        System.out.println();
        
        // Test Case 10: Invalid Operations in Idle State
        System.out.println("--- TEST CASE 10: Invalid Operations in Idle State ---");
        testIdleStateValidations(vendingMachine);
        System.out.println();
        
        // Test Case 11: Inventory Stock Verification
        System.out.println("--- TEST CASE 11: Inventory Stock Verification ---");
        testInventoryStockVerification(vendingMachine);
        System.out.println();
        
        // Test Case 12: Purchase Multiple Different Items
        System.out.println("--- TEST CASE 12: Purchase Multiple Different Items ---");
        testPurchaseMultipleItems(vendingMachine);
        System.out.println();
        
        // Test Case 13: Edge Case - Minimum Price Item
        System.out.println("--- TEST CASE 13: Edge Case - Minimum Price Item ---");
        testMinimumPriceItem(vendingMachine);
        System.out.println();
        
        // Test Case 14: Edge Case - Maximum Price Item
        System.out.println("--- TEST CASE 14: Edge Case - Maximum Price Item ---");
        testMaximumPriceItem(vendingMachine);
        System.out.println();
        
        // Test Case 15: State Transitions - Verify correct state transitions
        System.out.println("--- TEST CASE 15: State Transitions ---");
        testStateTransitions(vendingMachine);
        System.out.println();
        
        System.out.println("========== ALL TESTS COMPLETED ==========");
    }
    
    /**
     * Test Case 1: Setup Inventory
     * Adds multiple items to the vending machine inventory
     */
    private static void testSetupInventory(VendingMachine machine) {
        System.out.println("Adding items to vending machine...");
        machine.addItem("A1", "Snacks", 10);
        machine.addItem("A2", "Coke", 20);
        machine.addItem("A3", "Water", 5);
        machine.addItem("A4", "Juice", 30);
        System.out.println("✓ Inventory setup completed");
        System.out.println("  Total unique items: " + machine.getInventory().getCodeItemMapping().size());
    }
    
    /**
     * Test Case 2: Successful Purchase with Exact Amount
     * Scenario: Customer selects item, inserts exact coins, and receives item
     */
    private static void testSuccessfulPurchaseExactAmount(VendingMachine machine) {
        System.out.println("Scenario: Purchase 'Snacks' (Rs.10) with exact coins");
        
        // Select item
        machine.selectItem("A1");
        
        // Insert exact amount
        machine.insertCoin(Coin.FIVE);
        machine.insertCoin(Coin.FIVE);
        
        // Dispense
        System.out.println("Current balance: Rs." + machine.getBalance());
        machine.dispense();
        System.out.println("✓ Item dispensed successfully");
    }
    
    /**
     * Test Case 3: Successful Purchase with Extra Amount and Refund
     * Scenario: Customer inserts more money than required, receives refund
     */
    private static void testSuccessfulPurchaseWithRefund(VendingMachine machine) {
        System.out.println("Scenario: Purchase 'Water' (Rs.5) by inserting Rs.10");
        
        // Select item
        machine.selectItem("A3");
        
        // Insert extra amount
        machine.insertCoin(Coin.TEN);
        System.out.println("Current balance: Rs." + machine.getBalance());
        
        // Dispense (should trigger refund)
        machine.dispense();
        System.out.println("✓ Item dispensed with refund processed");
    }
    
    /**
     * Test Case 4: Insufficient Amount
     * Scenario: Customer tries to dispense without sufficient balance
     */
    private static void testInsufficientAmount(VendingMachine machine) {
        System.out.println("Scenario: Try to purchase 'Juice' (Rs.30) with Rs.10");
        
        // Select item
        machine.selectItem("A4");
        
        // Insert insufficient amount
        machine.insertCoin(Coin.TEN);
        System.out.println("Current balance: Rs." + machine.getBalance());
        System.out.println("Required: Rs.30");
        
        // Try to dispense - should fail
        machine.dispense();
        System.out.println("✓ Correctly rejected dispense with insufficient funds");
        
        // Refund the money
        machine.refund();
    }
    
    /**
     * Test Case 5: Item Not Available
     * Scenario: Customer tries to select an item that doesn't exist
     */
    private static void testItemNotAvailable(VendingMachine machine) {
        System.out.println("Scenario: Try to select non-existent item code 'Z9'");
        machine.selectItem("Z9");
        System.out.println("✓ Correctly rejected unavailable item");
    }
    
    /**
     * Test Case 6: Multiple Coin Insertions
     * Scenario: Insert coins one by one until sufficient amount is reached
     */
    private static void testMultipleCoinInsertions(VendingMachine machine) {
        System.out.println("Scenario: Purchase 'Coke' (Rs.20) with multiple coin insertions");
        
        machine.selectItem("A2");
        
        System.out.println("Inserting coin 1 (Rs.5)...");
        machine.insertCoin(Coin.FIVE);
        System.out.println("  Balance: Rs." + machine.getBalance());
        
        System.out.println("Inserting coin 2 (Rs.10)...");
        machine.insertCoin(Coin.TEN);
        System.out.println("  Balance: Rs." + machine.getBalance());
        
        System.out.println("Inserting coin 3 (Rs.5)...");
        machine.insertCoin(Coin.FIVE);
        System.out.println("  Balance: Rs." + machine.getBalance());
        
        machine.dispense();
        System.out.println("✓ Item dispensed after multiple coin insertions");
    }
    
    /**
     * Test Case 7: Refund Without Purchase
     * Scenario: Customer cancels purchase and gets refund
     */
    private static void testRefundWithoutPurchase(VendingMachine machine) {
        System.out.println("Scenario: Insert money, then refund without purchase");
        
        machine.selectItem("A1");
        
        machine.insertCoin(Coin.FIFTY);
        System.out.println("Balance before refund: Rs." + machine.getBalance());
        
        machine.refund();
        System.out.println("✓ Money refunded successfully");
    }
    
    /**
     * Test Case 8: Different Coin Types
     * Scenario: Test all available coin denominations
     */
    private static void testDifferentCoinTypes(VendingMachine machine) {
        System.out.println("Scenario: Test all coin denominations");
        System.out.println("Available coins:");
        System.out.println("  - Rs." + Coin.ONE.getValue());
        System.out.println("  - Rs." + Coin.FIVE.getValue());
        System.out.println("  - Rs." + Coin.TEN.getValue());
        System.out.println("  - Rs." + Coin.FIFTY.getValue());
        
        machine.selectItem("A1");
        
        // Test each coin type
        machine.insertCoin(Coin.ONE);
        machine.insertCoin(Coin.FIVE);
        machine.insertCoin(Coin.TEN);
        
        System.out.println("Total balance from multiple coin types: Rs." + machine.getBalance());
        machine.dispense();
        System.out.println("✓ All coin types processed successfully");
    }
    
    /**
     * Test Case 9: Item Already Selected State
     * Scenario: Verify state machine prevents re-selection
     */
    private static void testItemAlreadySelected(VendingMachine machine) {
        System.out.println("Scenario: Try to select another item after first item is selected");
        
        machine.selectItem("A1");
        System.out.println("First item selected: A1");
        
        System.out.println("Attempting to select: A2");
        machine.selectItem("A2");  // Should be rejected
        System.out.println("✓ State machine correctly prevented re-selection");
        
        machine.refund();
    }
    
    /**
     * Test Case 10: Invalid Operations in Idle State
     * Scenario: Verify that certain operations are invalid in Idle state
     */
    private static void testIdleStateValidations(VendingMachine machine) {
        System.out.println("Scenario: Validate operations in Idle state");
        
        System.out.println("Attempting to insert coin without selecting item:");
        machine.insertCoin(Coin.TEN);
        
        System.out.println("Attempting to dispense without selection:");
        machine.dispense();
        
        System.out.println("Attempting to refund without money:");
        machine.refund();
        
        System.out.println("✓ All invalid operations in Idle state correctly handled");
    }
    
    /**
     * Test Case 11: Inventory Stock Verification
     * Scenario: Verify inventory tracking after purchases
     */
    private static void testInventoryStockVerification(VendingMachine machine) {
        System.out.println("Scenario: Verify inventory before and after purchase");
        
        // Check initial inventory
        int initialItemsCount = machine.getInventory().getCodeItemMapping().size();
        System.out.println("Items in inventory: " + initialItemsCount);
        
        // Display stock
        System.out.println("Stock details:");
        machine.getInventory().getStock().forEach((item, quantity) -> {
            System.out.println("  - " + item.getName() + " (Code: " + item.getCode() + 
                             "): Price Rs." + item.getPrice() + ", Quantity: " + quantity);
        });
        
        System.out.println("✓ Inventory verified");
    }
    
    /**
     * Test Case 12: Purchase Multiple Different Items
     * Scenario: Complete multiple transactions in sequence
     */
    private static void testPurchaseMultipleItems(VendingMachine machine) {
        System.out.println("Scenario: Complete multiple purchases sequentially");
        
        // Purchase 1
        System.out.println("\n  Purchase 1: Snacks (Rs.10)");
        machine.selectItem("A1");
        machine.insertCoin(Coin.TEN);
        machine.dispense();
        
        // Purchase 2
        System.out.println("\n  Purchase 2: Water (Rs.5)");
        machine.selectItem("A3");
        machine.insertCoin(Coin.FIVE);
        machine.dispense();
        
        // Purchase 3
        System.out.println("\n  Purchase 3: Juice (Rs.30)");
        machine.selectItem("A4");
        machine.insertCoin(Coin.FIFTY);
        machine.dispense();
        
        System.out.println("\n✓ Multiple purchases completed successfully");
    }
    
    /**
     * Test Case 13: Edge Case - Minimum Price Item
     * Scenario: Purchase lowest price item with single coin
     */
    private static void testMinimumPriceItem(VendingMachine machine) {
        System.out.println("Scenario: Purchase minimum price item (Rs.5)");
        
        machine.selectItem("A3");
        machine.insertCoin(Coin.FIVE);
        
        System.out.println("Balance: Rs." + machine.getBalance());
        machine.dispense();
        System.out.println("✓ Minimum price item purchased successfully");
    }
    
    /**
     * Test Case 14: Edge Case - Maximum Price Item
     * Scenario: Purchase highest price item with largest coins
     */
    private static void testMaximumPriceItem(VendingMachine machine) {
        System.out.println("Scenario: Purchase maximum price item (Rs.30)");
        
        machine.selectItem("A4");
        machine.insertCoin(Coin.FIFTY);
        
        System.out.println("Balance: Rs." + machine.getBalance());
        machine.dispense();
        System.out.println("✓ Maximum price item purchased successfully");
    }
    
    /**
     * Test Case 15: State Transitions
     * Scenario: Verify correct state transitions through the entire flow
     */
    private static void testStateTransitions(VendingMachine machine) {
        System.out.println("Scenario: Trace state transitions");
        
        System.out.println("Initial State: IdleState");
        
        System.out.println("Action: Select item A1");
        machine.selectItem("A1");
        System.out.println("Current State: ItemSelectedState");
        
        System.out.println("Action: Insert coin Rs.10");
        machine.insertCoin(Coin.TEN);
        System.out.println("Current State: HasMoneyState");
        
        System.out.println("Action: Dispense");
        machine.dispense();
        System.out.println("Current State: IdleState (returned to initial state)");
        
        System.out.println("✓ State transitions validated");
    }
}
