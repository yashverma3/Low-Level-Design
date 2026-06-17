package InterviewProblems.Elevator;

import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import InterviewProblems.Elevator.entities.Request;
import InterviewProblems.Elevator.enums.Direction;
import InterviewProblems.Elevator.state.ElevatorState;
import InterviewProblems.Elevator.state.IdleState;

public class Elevator implements Runnable{
    private final int id;
    private AtomicInteger currentFloor;
    private ElevatorState state;
    private volatile boolean isRunning;

    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = new AtomicInteger(1);
        this.state = new IdleState();
        this.isRunning = true;
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>((a,b) -> b-a);
    }

    public void setState(ElevatorState state) {this.state = state;}

    public void move() {state.move(this);}

    // --- Request Handling ---
    public synchronized void addRequest(Request request) {
        System.out.println("Elevator " + id + " processing: " + request);
        state.addRequest(this, request);
    }

    // --- Getters and Setters ---
    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor.get(); }
    public void setCurrentFloor(int floor) {this.currentFloor.set(floor);}

    public Direction getDirection() { return state.getDirection(); }
    public TreeSet<Integer> getUpRequests() { return upRequests; }
    public TreeSet<Integer> getDownRequests() { return downRequests; }
    public boolean isRunning() { return isRunning; }
    public void stopElevator() { this.isRunning = false; }

    @Override
    public void run() {
        while (isRunning) {
            move();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
