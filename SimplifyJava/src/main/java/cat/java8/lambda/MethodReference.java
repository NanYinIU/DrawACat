package cat.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * day3
 * 方法与构造器引用
 * Java8之后允许通过使用 :: 为方法传入 【方法或构造器的引用】
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-22
 */
public class MethodReference {
    public MethodReference() {
    }

    public static void main(String[] args) {
        Weapon weapon = new Weapon("tes");
        // 提供了函数式接口的参数
        Weapon emptyWeapon = weapon.create(Weapon::new);
        emptyWeapon.setName("tes2");

        List<Weapon> list = new ArrayList<>();
        list.add(weapon);
        list.add(emptyWeapon);
        // forEach 提供了 Consumer 参数
        list.forEach(weapon::attach);


    }

    static class Weapon {

        public Weapon() {
        }

        public Weapon(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Weapon create(final Supplier<Weapon> supplier) {
            return supplier.get();
        }

        public void attach(final Weapon weapon){
            System.out.println(weapon.getName());
        }

        public void enchanted(){

        }
    }
}
