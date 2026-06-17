package InterviewProblems.CachingSystem.cache;

public interface Cache {
    void insert(int key, int value);
    int get(int key);
    void print();
}
