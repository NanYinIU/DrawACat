package cat.jvm;

/**
 * 对象实例化过程，通过javap反编译字节码获取字节码信息，
 * 原来在执行init之前需要做这么多操作
 *
 * 1. 类加载检查，检查类是否已经被加载到内存中
 * 2. 分配内存，使用【指针碰撞】或【内存表】的方式进行内存的分配
 * 3. 初始化零值，初始化对象默认值
 * 4. 设置对象头
 * 5. 执行init函数
 *
 * 0 new #2 <java/lang/Object>
 * 3 dup
 * 4 invokespecial #1 <java/lang/Object.<init>>
 * 7 astore_1
 * 8 return
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-10-13
 */
public class ObjectLoadOrder {
    public static void main(String[] args) {
        Object o = new Object();
    }
}
