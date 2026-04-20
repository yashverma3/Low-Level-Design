# ISP - Interface Segregation Principle

## Clients should not be forced to implement interfaces they don’t use.

### Bad Design
- Robot forced to implement irrelevant method, i.e eat()

### Good Design
- Segregated the interfaces according to their methods
- Robot and human implements their corresponding interfaces and relevant methods