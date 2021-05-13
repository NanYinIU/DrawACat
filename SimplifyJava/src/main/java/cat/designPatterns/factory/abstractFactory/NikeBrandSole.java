package cat.designPatterns.factory.abstractFactory;

public class NikeBrandSole implements Sole{
    @Override
    public void getMaterial() {
        // 橡胶鞋底
        System.out.println("rubber");
    }
}
