package chapterseven;

/**
 * @Author: HB
 * @Description:  被动引用：所有引用类型的方式都不会触发初始化动作
 *                常量在编译阶段会存入调用类的常量池中,本质上没有直接引用到定义常量的类,不会触发该类的初始化
 * @CreateDate: 10:07 2020/3/9
 */

public class ClassLoadingTest2 {
    static class ConstClass{
        static {
            System.out.println("ConstClass init.");
        }
        public static final String STRING = "HELLO";
    }

    public static void main(String[] args) {
        System.out.println(ConstClass.STRING);
    }
}
