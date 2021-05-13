package cat.designPatterns.bridge;

public class ColorBlue implements Colors {
    @Override
    public void paint() {
        System.out.println(" print blue !!");
    }
}
