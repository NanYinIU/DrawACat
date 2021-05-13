package cat.designPatterns.factory.simpleFactory;

public class RedShoes implements Shoes {
    @Override
    public void getColor() {
        System.out.println(" is red color shoes");
    }
}
