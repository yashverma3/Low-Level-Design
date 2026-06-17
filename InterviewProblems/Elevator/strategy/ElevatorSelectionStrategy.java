package InterviewProblems.Elevator.strategy;

import java.util.List;
import java.util.Optional;

import InterviewProblems.Elevator.Elevator;
import InterviewProblems.Elevator.entities.Request;

public interface ElevatorSelectionStrategy {
    Optional<Elevator> selectElevator(List<Elevator> elevators, Request request);
}
