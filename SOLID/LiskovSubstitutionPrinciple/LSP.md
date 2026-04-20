# LSP - Liskov Substitution Principle

## Child classes should be replaceable for their base class without breaking behaviour

### Bad Design
- Credit Card Refund works fine
- UPI payment throws an error
- UPI class cannot replace Payment class safely

### Good Design
- No class is forced to implement unsupported method
- No runtime errors
- Substitution of base class with child class is possible