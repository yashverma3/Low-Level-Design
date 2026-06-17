package InterviewProblems.CachingSystem.nodes;

public abstract class Node {
    public int key;
    public int value;
    public Node prev;
    public Node next;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        prev = null;
        next = null;
    }
}