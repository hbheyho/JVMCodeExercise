package chapterfour.JHSDB;


/**
 * @Author: HB
 * @Description: 通过JHSDB工具分析staticObj, instanceObj, localObj变量的存储位置, 对于他们指向的实例存放在堆中
 *               VM Args: -Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops(启用压缩指针)
 *               - JDK 9中集成了JHSDB分析工具,需要在JDK 9下进行测试
 * @CreateDate: 11:00 2020/2/28
 */

public class JHSDBTestCase {

    static class Test {
        // staticObj存放在方法区中（JDK 7以前, JDK 7以后的HotSpot虚拟机存放在java堆中）,
        static ObjectHolder staticObj = new ObjectHolder();
        // instanceObj存放java堆中
        ObjectHolder instanceObj = new ObjectHolder();
        void foo(){
            // localObj存放在foo()方法栈帧的局部变量表中
            ObjectHolder localObj = new ObjectHolder();
            // 设置一个断点,便于进行调试
            System.out.println("done");
        }
    }

    private static class ObjectHolder{}

    public static void main(String[] args) {
        Test test = new JHSDBTestCase.Test();
        test.foo();
    }
}
