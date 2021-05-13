package cat.designPatterns.facade;

public class App {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.startService();
        facade.stopService();
    }
}
