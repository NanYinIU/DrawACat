package cat.designPatterns.factory.abstractFactory;

public interface ShoesFactory {
    public Sole createSole();
    public Shoelace createShoelace();
}
