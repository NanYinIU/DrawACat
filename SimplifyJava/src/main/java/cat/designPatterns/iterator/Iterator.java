package cat.designPatterns.iterator;

public interface Iterator<E> {
    boolean hasNext();
    E next();
}
