package InterfaceSegregationPrinciple.Violation;

interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    @Override
    public void work() {
        System.out.println("Robot Working");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robot doesn't eat");
    }
}

class Human implements Worker {
    @Override
    public void work() {
        System.out.println("Human Working");
    }

    @Override
    public void eat() {
        System.out.println("Human Eating");
    }
}

public class Main {
    public static void main(String[] args) {
        Human human = new Human();
        human.work();
        human.eat();
        
        Robot robot = new Robot();
        robot.work();
        robot.eat();

    }
}
