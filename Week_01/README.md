学习笔记
一、字节码指令类型
    1、栈操作指令，包括与局部变量交互的指令（iload,istore)
    2、程序流程控制指令(iflt,goto)
    3、对象操作指令，包括方法调用(new,invokestatic)
    4、算术运算以及类型转换指令(iadd,i2d)
    
二、jvm命令行工具
    jps/jinfo   查看java进程
    jstat       查看JVM内部gc信息
    jmap        查看heap或类占用空间统计
    jstack      查看线程信息
    jcmd        执行JVM相关分析命令（整合命令）
    jrunscript/jjs  执行js命令
    
    重要命令：
    1、jps -mlv
    2、jstat -gcutil pid 1000 1000
    3、jmap -heap pid 
       jmap -histo pid
    4、jstack -l pid
    
三、jvm图形化工具
    jconsole
    jvisualvm
    visualGC
    jmc
    
四、GC回收算法
    现在普遍使用分代回收
        年轻代：标记复制算法，edn区和survivor区存活的对象，复制到另外一个空的survivor区
        老年代：标记整理算法，将存活的对象，移动到内存的另外一头 然后回收边界以外的内存
        
五、GC种类和特点
    1、串行GC（Serial GC）：单线程，应用会暂停
    2、并行GC(ParNew、Parallel Scavenge、Parallel Old)：多线程并行执行垃圾回收，关注于高吞吐量
    3、CMS：多线程并发标记清除，关注于减低延迟
    4、G1：通过划分多个内存区域做增量整理和回收，进一步减低延迟
    5、ZGC：通过着色指针和读屏障，实现几乎全部的并发执行，几毫秒的延迟，线性可扩展
    6、Epsilon: 实验性GC，供性能分析使用
    7、Shenandoah: G1的改进版和ZGC类似