package InterviewProblems.Elevator.state;

import InterviewProblems.Elevator.Elevator;
import InterviewProblems.Elevator.entities.Request;
import InterviewProblems.Elevator.enums.Direction;

public class IdleState implements ElevatorState {
    @Override
    public void move(Elevator elevator) {
        if(!elevator.getDownRequests().isEmpty()) {
            elevator.setState(new MovingDownState());
        } else if(!elevator.getUpRequests().isEmpty()) {
            elevator.setState(new MovingUpState());
        }
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        if(elevator.getCurrentFloor() < request.getTargetFloor()) {
            elevator.getUpRequests().add(request.getTargetFloor());
        } else if(elevator.getCurrentFloor() > request.getTargetFloor()) {
            elevator.getDownRequests().add(request.getTargetFloor());
        }
    }

    @Override
    public Direction getDirection() {
        return Direction.IDLE;
    }
}
