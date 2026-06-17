package InterviewProblems.Elevator.state;

import InterviewProblems.Elevator.Elevator;
import InterviewProblems.Elevator.entities.Request;
import InterviewProblems.Elevator.enums.Direction;

public interface ElevatorState {
    void move(Elevator elevator);
    void addRequest(Elevator elevator, Request request);
    Direction getDirection();
}
