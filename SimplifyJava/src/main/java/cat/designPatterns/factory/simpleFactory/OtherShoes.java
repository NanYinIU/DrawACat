package cat.designPatterns.factory.simpleFactory;

public class OtherShoes implements Shoes {
    @Override
    public void getColor() {
        System.out.println(" is other color shoes");
    }
}
