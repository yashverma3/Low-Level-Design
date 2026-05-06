package InterviewProblems.SnakeAndLadder.entities;

public class Ladder extends BoardEntity{
    public Ladder(int start, int end) {
        super(start,end);
        if(start>=end) {
            throw new IllegalArgumentException("Snake head must be at a higher position than its tail.");
        }
    }
}
