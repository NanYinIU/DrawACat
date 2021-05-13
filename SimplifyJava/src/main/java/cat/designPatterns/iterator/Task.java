package cat.designPatterns.iterator;

public interface Task {
    void add(int mark);
    void removeLast();
    Iterator<Object> iterator();
}
