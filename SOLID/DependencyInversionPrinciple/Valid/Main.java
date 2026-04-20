package DependencyInversionPrinciple.Valid;

interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("Connected to MySQL");
    }
}

class MongoDBDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("Connected to MongoDB");
    }
}

class Application {
    private Database db;

    public Application(Database database) {
        this.db = database;
    }
    public void start() {
        db.connect();
    }
}

public class Main {
    public static void main(String[] args) {
        Application app1 = new Application(new MySQLDatabase());
        app1.start();

        Application app2 = new Application(new MongoDBDatabase());
        app2.start();
    }
}