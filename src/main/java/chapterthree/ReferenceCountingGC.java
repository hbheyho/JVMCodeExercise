package chapterthree;

/**
 * @Author: HB
 * @Description:  引用计数算法测试
 *                objA和objB相互引用，遂引用计数都不为0，但是查看GC日志可知，GC对其进行了收回,说明Hotspot垃圾回收并不是使用引用计数算法
 *                - 增加 -XX:+PrintGCDetails可查看垃圾回收日志
 * @CreateDate: 11:57 2020/2/19
 */

public class ReferenceCountingGC {
    public Object instance = null;

    // 占据内存，以便在GC日志中看清楚是否有回收过
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        // 相互引用，两个对象的引用计数器都不为0，若使用引用技术算法，则GC无法回收该两个对象
        objA.instance = objB;
        objB.instance = objA;

        // 对象置空，对象不能再访问
        objA = null;
        objB = null;

        // 进行垃圾回收
        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }
}
