package InterviewProblems.Elevator.state;

import java.util.TreeSet;

import InterviewProblems.Elevator.Elevator;
import InterviewProblems.Elevator.entities.Request;
import InterviewProblems.Elevator.enums.Direction;
import InterviewProblems.Elevator.enums.RequestSource;

public class MovingUpState implements ElevatorState {
    @Override
    public void move(Elevator elevator) {
        TreeSet<Integer> upRequests = elevator.getUpRequests();
        if(upRequests.isEmpty()) {
            elevator.setState(new IdleState());
            return;
        }

        Integer nextFloor = upRequests.first();
        Integer currentFloor = elevator.getCurrentFloor();
        elevator.setCurrentFloor(currentFloor + 1);

        if(nextFloor == elevator.getCurrentFloor()) {
            System.out.println("Elevator " + elevator.getId() + " stopped at floor " + nextFloor);
            upRequests.pollFirst();
        }

        if(upRequests.isEmpty()) {
            elevator.setState(new IdleState());
        }
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        // Internal requests
        if(request.getRequestSource() == RequestSource.INTERNAL) {
            if(elevator.getCurrentFloor() < request.getTargetFloor()) {
                elevator.getUpRequests().add(request.getTargetFloor());
            } else {
                elevator.getDownRequests().add(request.getTargetFloor());
            }
            return;
        }

        // External requests
        if(request.getDirection() == Direction.UP && elevator.getCurrentFloor() <= request.getTargetFloor()) {
            elevator.getUpRequests().add(request.getTargetFloor());
        } else if (request.getDirection() == Direction.DOWN) {
            elevator.getDownRequests().add(request.getTargetFloor());
        }
    }

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }
}
