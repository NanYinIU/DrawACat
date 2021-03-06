package cat.designPatterns.command;

public class AttackCommand implements Command{

    Soldier soldier ;

    public AttackCommand(Soldier soldier) {
        this.soldier = soldier;
    }

    @Override
    public void excute() {
        soldier.prepareWeapon();
        soldier.attack();
    }

}
