package cat.pattern.command;

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
