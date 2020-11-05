package cat.pattern.factory.abstractFactory;

public interface ShoesFactory {
    public Sole createSole();
    public Shoelace createShoelace();
}
