package cat.jvm.javap;

/**
 * 1. java SyncTest.java 得到class 文件
 * 2. 使用javap -c SyncTest 得到反编译的内容，可以使用流符号输出到文件中，如 > a.txt
 * > javap更详细的信息可以使用 javap -help 查看，输出更多相关的信息，比如本地变量表
 * > 或者使用idea插件jclasslib进行查看
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-10-07
 */
public class SyncTest {

    private static final Object object = new Object();

    public static void main (String[] args) {
        // 修饰代码块,javap反编译后，可以看到是使用monitorenter、monitorexit两条命令控制
        synchronized ( object ) {
            System.out.println( "abc" );
        }
        // 修饰方法，
        SyncTest instance = new SyncTest();
        int c = instance.add(1,2);
        System.out.println(c);

    }

    // add方法
    private synchronized int add(int a,int b){
        int x = 10;
       return x+a+b;
    }

}
