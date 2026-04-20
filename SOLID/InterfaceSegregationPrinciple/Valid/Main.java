package InterfaceSegregationPrinciple.Valid;

interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Robot implements Workable {
    @Override
    public void work() {
        System.out.println("Robot Working");
    }
}

class Human implements Workable, Eatable {
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
    }
}
