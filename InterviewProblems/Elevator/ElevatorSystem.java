package InterviewProblems.Elevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import InterviewProblems.Elevator.entities.Request;
import InterviewProblems.Elevator.enums.Direction;
import InterviewProblems.Elevator.enums.RequestSource;
import InterviewProblems.Elevator.strategy.ElevatorSelectionStrategy;
import InterviewProblems.Elevator.strategy.NearestElevatorStrategy;

public class ElevatorSystem {
    private static ElevatorSystem instance;
    private Map<Integer, Elevator> elevators;
    private final ElevatorSelectionStrategy strategy;
    private final ExecutorService service;

    private ElevatorSystem(int numElevators) {
        this.strategy = new NearestElevatorStrategy();
        this.service = Executors.newFixedThreadPool(numElevators);

        elevators = new HashMap<>();

        for(int i=1; i<=numElevators; i++) {
            elevators.put(i, new Elevator(i));
        }
    }

    public static synchronized ElevatorSystem getInstance(int numElevators) {
        if(instance == null) {
            instance = new ElevatorSystem(numElevators);
        }
        return instance;
    }

    public void start() {
        for(Elevator elevator: elevators.values()) {
            service.submit(elevator);
        }
    }

    public void shutdown() {
        for(Elevator elevator: elevators.values()) {
            elevator.stopElevator();
        }
        service.shutdown();
    }

    // External Call
    public void requestElevator(int floor, Direction direction) {
        System.out.println("\n>> EXTERNAL Request: User at floor " + floor + " wants to go " + direction);
        Request request = new Request(floor, direction, RequestSource.EXTERNAL);

        Optional<Elevator> selectedElevator = strategy.selectElevator(new ArrayList<>(elevators.values()), request);
        if(selectedElevator.isPresent()) {
            selectedElevator.get().addRequest(request);
        } else {
            System.out.println("System busy, please wait.");
        }
    }

    // Internal Call
    public void selectFloor(int elevatorId, int floor) {
        System.out.println("\n>> INTERNAL Request: User in Elevator " + elevatorId + " selected floor " + floor);
        Request request = new Request(floor, Direction.IDLE, RequestSource.INTERNAL);
        
        Elevator elevator = elevators.get(elevatorId);
        if(elevator != null) {
            elevator.addRequest(request);
        } else {
            System.out.println("Invalid Elevator ID");
        }
    }
}
