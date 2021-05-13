package cat.designPatterns.command;

public class RetreatCommand implements Command{

    Soldier soldier ;

    public RetreatCommand(Soldier soldier) {
        this.soldier = soldier;
    }

    @Override
    public void excute() {
        soldier.retreat();
    }
}
