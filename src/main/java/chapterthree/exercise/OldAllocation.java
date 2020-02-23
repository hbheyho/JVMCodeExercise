package chapterthree.exercise;

/**
 * @Author: HB
 * @Description:  大对象直接分配在老年代 - serial 和 serial old垃圾回收器
 *                - VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 *                           -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728(3M)
 *                当大对象大于PretenureSizeThreshold设置的值，直接把对象分配到老年代
 *                - 很奇怪：为什么eden要莫名占了30%的内存？？明明全部内存分配到老年代
 * @CreateDate: 12:06 2020/2/23
 */

public class OldAllocation {
    private static final int _1MB = 1024 * 1024;
    public static void testAllocation(){
        byte[] allocation;
        allocation = new byte[6 * _1MB];
    }

    public static void main(String[] args) {
        OldAllocation.testAllocation();
    }
}
