package cat.designPatterns.factory.simpleFactory;

public class WhiteShoes implements Shoes {
    @Override
    public void getColor() {
        System.out.println("is white color shoes");
    }
}
