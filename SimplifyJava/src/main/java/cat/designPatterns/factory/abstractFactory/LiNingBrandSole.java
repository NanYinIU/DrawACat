package cat.designPatterns.factory.abstractFactory;

public class LiNingBrandSole implements Sole {
    @Override
    public void getMaterial() {
        System.out.println("air cushion");
    }
}
