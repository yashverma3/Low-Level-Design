# SRP - Single Responsibility Principle

## A class should have only one reason to change

### Bad Design
- Business logic + printing + persistence all in one class
- Changing DB logic → modifies Invoice
- Changing print format → modifies Invoice

### Good Design
- Each class has one responsibility
- Easier testing, modification, scaling