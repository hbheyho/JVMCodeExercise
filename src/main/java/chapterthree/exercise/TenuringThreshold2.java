package chapterthree.exercise;

/**
 * @Author: HB
 * @Description: 动态对象年龄判断 - 不需达到MaxTenuringThreshold参数才进入老年代
 *               -VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 *               -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=15
 *               当Survivor空间中相同年龄的所有对象大小总和大于Survivor空间的一半,则年龄大于或等于该年龄的
 *               对象直接进入到老年代，而无需达到MaxTenuringThreshold要求
 * @CreateDate: 12:53 2020/2/23
 */

public class TenuringThreshold2 {
    private static final int _1MB = 1024 * 1024;
    public static void testTenuringThreshold2(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];  // allocation1 + allocation2大小大于Survivor空间一半
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        TenuringThreshold2.testTenuringThreshold2();
    }
}
