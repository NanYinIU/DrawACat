package cat.designPatterns.memento;

public interface Game {
    void storeGamePoint(Memento memento);
    Memento retrieveGamePoint();
}
