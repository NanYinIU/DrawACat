package cat.designPatterns.factory.factoryMethod;

public class WhiteShoes implements Shoes {
    @Override
    public void getColor() {
        System.out.println("is white color shoes");
    }
}
