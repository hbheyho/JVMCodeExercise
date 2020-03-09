package chapterseven;

/**
 * @Author: HB
 * @Description:  被动引用：所有引用类型的方式都不会触发初始化动作
 *                Test1 - 对于静态字段,只有直接定义这个字段的类才会被初始化,遂下述SubClass.value引起父类的初始化
 *                对于HotSpot虚拟机来说,下述操作会触发类加载 -XX:+TraceClassLoading进行查看
 *                Test2 - SuperClass并没有初始化, 但是触发了一个名为[Lchapterseven.SuperClass的类初始化,表示一个
 *                类型为chapterseven.SuperClass的一维数组,它由虚拟机自动生成,直接继承于java.lang.object的子类,创
 *                建动作由字节码指令newarray执行
 * @CreateDate: 9:50 2020/3/9
 */

public class ClassLoadingTest1 {
    static class SuperClass{
        static {
            System.out.println("SuperClass init.");
        }
        public static int value = 123;
    }

    static class SubClass extends SuperClass{
        static {
            System.out.println("SubClass init.");
        }
    }

    public static void main(String[] args) {
        /* Test1 -
        System.out.println(SubClass.value);*/

        // Test2
        SuperClass[] sca = new SuperClass[10];
    }

}
