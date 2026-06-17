package InterviewProblems.CachingSystem.nodes;

public class LFU_Node extends Node {
    public int frequency;

    public LFU_Node(int key, int value, int frequency) {
        super(key, value);
        this.frequency = frequency;
    }
}
