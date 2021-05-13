package cat.designPatterns.v2.factory.methodFactory;


public interface WeaponFactory {
    public Weapon build(boolean isEnchanted);
}
