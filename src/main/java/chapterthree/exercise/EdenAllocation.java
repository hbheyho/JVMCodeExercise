package chapterthree.exercise;

/**
 * @Author: HB
 * @Description:  对象优先分配Eden - serial 和 serial old垃圾回收器
 *                JVM Args: -verbose:gc -Xms20M -Xmx20M -Xmn(设置新生代)10M -XX:+PrintGCDetails
 *                -XX:SurvivorRatio=8(设置两个Survivor区和eden的比值) -XX:+UseSerialGC
 *                JDK 8 服务器模式下默认GC为 Parallel Scavenge + serial Old, 本次测试使用-XX:+UseSerialGC修改
 *                GC为serial + serial Old,不修改也可，两者对于新生代都使用的使标记-复制算法,使用-XX:+PrintCommandLineFlags
 *                可以得到使用了何种虚拟机
 *                * 可能由于JDK 版本原因, 在分配allocation3会提前触发GC收集
 * @CreateDate: 10:21 2020/2/23
 */

public class EdenAllocation {
    private static final int _1MB = 1024 * 1024;
    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        // 此时将会进行一次Minor GC, 根据所设置的参数, 新年代可用的空间为9216KB(一个Eden空间 + 1个Survivor空间)
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        EdenAllocation.testAllocation();
    }
}
