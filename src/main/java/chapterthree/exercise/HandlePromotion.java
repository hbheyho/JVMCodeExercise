package chapterthree.exercise;

/**
 * @Author: HB
 * @Description: 空间分配担保
 *               -VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 *               -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:HandlePromotionFailure=true/false
 *               在发生Minor GC之前,虚拟机必须检查老年代最大可用的连续空间是否大于新生代所有对象总空间,若成立,
 *               则可以正常进行GC, 不然检查是否设置了HandlePromotionFailure参数,若为true,则继续检查老年代最大
 *               可用的连续空间是否大于历次晋升到老年代对象的平均大小,若大于,则进行一次GC,虽然任存在危险,否则进
 *               行Full GC
 *               JDK 6 Update 24改为只要老年代最大可用的连续空间是否大于新生代所有对象总空间/历次晋升到老年代对
 *               象的平均大小,则进行一次Minor GC,不受HandlePromotionFailure参数影响。且在JDK 8的HotSpot虚拟机中
 *               Unrecognized VM option 'HandlePromotionFailure'错误
 * @CreateDate: 13:06 2020/2/23
 */

public class HandlePromotion {
    private static final int _1MB = 1024 * 1024;
    public static void testHandlePromotion(){
        byte[] allocation1,allocation2,allocation3,allocation4,
                allocation5,allocation6,allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }

    public static void main(String[] args) {
        HandlePromotion.testHandlePromotion();
    }
}
