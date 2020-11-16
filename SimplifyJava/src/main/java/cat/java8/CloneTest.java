package cat.java8;

import org.junit.Test;

/**
 * @author nanyin
 * @class CloneTest.java
 * @description Java 深克隆 浅克隆
 * @create 11:02 2020-04-15
 */
public class CloneTest implements Cloneable {

    public int x;
    public SonClone son;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public SonClone getSon() {
        return son;
    }

    public void setSon(SonClone son) {
        this.son = son;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Test
    public void testClone(){
        CloneTest test = new CloneTest();
        test.setX(127);
        test.setSon(new SonClone());
        try {
            CloneTest clone = (CloneTest) test.clone();
            // 比较test和复制对象copy
            System.out.println("test == clone --> "
                    +(test == clone));
            System.out.println("test.hash == clone.hash --> "
                    +(test.hashCode() == clone.hashCode()));

            System.out.println("test.getClass() == clone.getClass() --> "
                    + (test.getClass() == clone.getClass()));
            System.out.println("test.x == clone.x --> "
                    +(test.getX() == clone.getX()));

            System.out.println("test.son == clone.son --> "
                    +(test.getSon() == clone.getSon()));
            System.out.println("test.son.hash == clone.son.hash --> "
                    +(test.getSon().hashCode() == clone.getSon().hashCode()));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    class SonClone implements Cloneable{
        int a;
    }

}
