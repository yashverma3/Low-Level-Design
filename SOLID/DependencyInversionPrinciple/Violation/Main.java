package DependencyInversionPrinciple.Violation;

class MySQLDatabase {
    public void connect() {
        System.out.println("Connected to MySQL");
    }
}

class Application {
    private MySQLDatabase db = new MySQLDatabase();

    public void start() {
        db.connect();
    }
}

public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }
}
