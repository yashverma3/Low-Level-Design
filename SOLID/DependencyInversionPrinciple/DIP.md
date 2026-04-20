# DIP - Dependency Inversion Principle

## High-level modules should depend on abstractions, and concrete implementations should be injected (e.g via constructor) rather than created inside them

### Bad Design
- Tight coupling
- Hardcoded dependency
- Switching DB → modify Application

### Good Design
- Use constructor injection by providing the type of object created at runtime
- High-level modules → depend on interfaces