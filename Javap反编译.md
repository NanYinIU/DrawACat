# Javap反编译

在分析代码的调用过程、分析代码用到的常量池信息等。可以使用javap命令反编译class文件

## 步骤

1. `java SyncTest.java` 得到 class 文件
2. 使用 `javap -c -s -p -l -v SyncTest` 得到反编译的内容，可以使用流符号输出到文件中，如 在后面添加 `> a.txt`

> javap更详细的信息可以使用 javap -help 查看，输出更多相关的信息，比如本地变量表。
> 或者使用idea插件jclasslib进行查看

## 实例

可以使用Javap分析synchronized关键字的相关字节码信息。

原java代码为：

```java
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
```

先通过javac编译,然后通过 `javap -c -s -p -l -v SyncTest > c.txt`命令输出到文件中，可以看到相关常量池、字节码等信息。

```java
Classfile /Users/gaoguoxing/Downloads/SyncTest.class
  Last modified 2020-10-7; size 805 bytes
  MD5 checksum 5ed577c095cb66b371121b71cfb86e6f
  Compiled from "SyncTest.java"
public class com.nanyin.javap.SyncTest
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #10.#28        // java/lang/Object."<init>":()V
   #2 = Fieldref           #6.#29         // 
     // ......略
{
  private static final java.lang.Object object;
    descriptor: Ljava/lang/Object;
    flags: ACC_PRIVATE, ACC_STATIC, ACC_FINAL

  public com.nanyin.javap.SyncTest();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 11: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=3, args_size=1
         0: getstatic     #2                  // Field object:Ljava/lang/Object;
         3: dup
         4: astore_1
         5: monitorenter
         6: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
         9: ldc           #4                  // String abc
        11: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        14: aload_1
        15: monitorexit
        16: goto          24
        19: astore_2
        20: aload_1
        21: monitorexit
        22: aload_2
        23: athrow
        24: new           #6                  // class com/nanyin/javap/SyncTest
        27: dup
        28: invokespecial #7                  // Method "<init>":()V
        31: astore_1
        32: aload_1
        33: iconst_1
        34: iconst_2
        35: invokespecial #8                  // Method add:(II)I
        38: istore_2
        39: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
        42: iload_2
        43: invokevirtual #9                  // Method java/io/PrintStream.println:(I)V
        46: return
      Exception table:
         from    to  target type
             6    16    19   any
            19    22    19   any
      LineNumberTable:
        line 17: 0
        line 18: 6
        line 19: 14
        line 21: 24
        line 22: 32
        line 23: 39
        line 25: 46
      StackMapTable: number_of_entries = 2
        frame_type = 255 /* full_frame */
          offset_delta = 19
          locals = [ class "[Ljava/lang/String;", class java/lang/Object ]
          stack = [ class java/lang/Throwable ]
        frame_type = 250 /* chop */
          offset_delta = 4

  private synchronized int add(int, int);
    descriptor: (II)I
    flags: ACC_PRIVATE, ACC_SYNCHRONIZED
    Code:
      stack=2, locals=4, args_size=3
         0: bipush        10
         2: istore_3
         3: iload_3
         4: iload_1
         5: iadd
         6: iload_2
         7: iadd
         8: ireturn
      LineNumberTable:
        line 29: 0
        line 30: 3

  static {};
    descriptor: ()V
    flags: ACC_STATIC
    Code:
      stack=2, locals=0, args_size=0
         0: new           #10                 // class java/lang/Object
         3: dup
         4: invokespecial #1                  // Method java/lang/Object."<init>":()V
         7: putstatic     #2                  // Field object:Ljava/lang/Object;
        10: return
      LineNumberTable:
        line 13: 0
}
SourceFile: "SyncTest.java"
```

通过上面的信息可以看到，代码块中的synchronized关键字使用monitorenter、monitorexit控制。而synchronized修饰方法则使用ACC_SYNCHRONIZED的flag实现。

## 参考

如何理解Java字节码：https://juejin.im/post/6844903588716609543

