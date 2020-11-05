package cat.pattern.memento;

public interface Game {
    void storeGamePoint(Memento memento);
    Memento retrieveGamePoint();
}
