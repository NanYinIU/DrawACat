package cat.pattern.bridge;

public class ColorRed implements Colors {
    @Override
    public void paint() {
        System.out.println(" paint inner with red !!");
    }
}
