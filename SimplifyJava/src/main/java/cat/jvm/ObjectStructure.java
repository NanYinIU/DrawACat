package cat.jvm;

import org.omg.CORBA.Current;
import org.openjdk.jol.datamodel.CurrentDataModel;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.layouters.CurrentLayouter;
import org.openjdk.jol.layouters.HotSpotLayouter;
import org.openjdk.jol.layouters.Layouter;
import org.openjdk.jol.vm.VM;

/**
 * 使用jol（Java Object Layout）工具，分析内存结构占比
 *
 * 分为header、padding、data(实际数据)
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-10-09
 */
public class ObjectStructure {
    public static void main(String[] args) {

        // 默认 new Object 空对象会占用16个字节
        // 1. header头部的markword 8字节
        // 2. header头部中的klasspointer 使用 JVM参数 -XX:+UseCompressedOops 压缩普通对象（ordinary object pointer）指针，开启指针压缩占用4字节，不开启占用8字节。
        //      - 为什么要压缩指针，因为32位系统上面指针寻址的范围是4个字节，在迁移到64位的机器上面，寻址的长度变为原来的2倍，会占用更多的空间。
        // 3. 如果是数组，则额外增加[数组长度]
        // 4. padding对其字节，为了让对象开始地址是字节的整数倍，所以将所有对象所占的大小也要对其为8的倍数。开启指针压缩后，markword和klasspointer占用了12个字节，所以padding占用4个字节进行补齐。
        // 5. data实际数据

        Object o = new Object();
        // o = 12l;
        // 运行下面的代码进行证明
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        int hashCode = System.identityHashCode(o);
//        System.out.println(hashCode);
        System.gc();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        synchronized (o){
//            System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        }

//        int a = 0;
//        // int 占用4个字节
//        System.out.println(ClassLayout.parseInstance(a).toPrintable());
//
//        // 查看数组占用内存  16 + 48
//        int[] arr = new int[12];
//        System.out.println(arr[0]);
//        System.out.println(ClassLayout.parseInstance(arr).toPrintable());

//        double b = 5L;
//        System.out.println(ClassLayout.parseInstance(b).toPrintable());
//        byte byt = 'c';
//        System.out.println(ClassLayout.parseInstance(byt).toPrintable());
    }
}
