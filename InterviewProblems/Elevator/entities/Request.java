package InterviewProblems.Elevator.entities;

import InterviewProblems.Elevator.enums.Direction;
import InterviewProblems.Elevator.enums.RequestSource;

public class Request {
    private final int targetFloor;
    private final Direction direction;
    private final RequestSource source;

    public Request(int targetFloor, Direction direction, RequestSource source) {
        this.targetFloor = targetFloor;
        this.direction = direction;
        this.source = source;
    }

    public int getTargetFloor() {
        return this.targetFloor;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public RequestSource getRequestSource() {
        return this.source;
    }

    @Override
    public String toString() {
        return source + " Request to floor " + targetFloor + (source == RequestSource.EXTERNAL ? " going " + direction : "");
    }
}
