package chapterthree.exercise;

/**
 * @Author: HB
 * @Description:  长期存活对象进入老年代 - MaxTenuringThreshold参数使用
 *                -VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 *                          -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=1/15
 *                          每个对象的对象头有个age属性,如果经过第一次Minor GC之后,对象任然存活且可以进行Survivor区域,
 *                          则将其年龄设置为1岁,每熬过一次Minor GC,便年龄加1,直到达到MaxTenuringThreshold设置的值便
 *                          进入老年代
 * @CreateDate: 12:28 2020/2/23
 */

public class TenuringThreshold {
    private static final int _1MB = 1024 * 1024;
    public static void testTenuringThreshold(){
        byte[] allocation1,allocation2,allocation3;
        allocation1 = new byte[_1MB/4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        TenuringThreshold.testTenuringThreshold();
    }
}
