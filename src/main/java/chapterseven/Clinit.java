package chapterseven;

/**
 * @Author: HB
 * @Description: <clinit>()方法执行顺序,同一个加载器下,一个类型只会初始化一次,即<clinit>()方法只执行一次
 *                - java虚拟机会保证子类的<clinit>()方法执行前,父类的<clinit>()方法已经执行完毕
 *                - 执行接口的<clinit>()方法不需要先执行父类的<clinit>()方法, 接口的实现类在初始化时也不会
 *                执行接口的<clinit>()方法
 * @CreateDate: 15:36 2020/3/9
 */

public class Clinit {
    static class parent{
        public static int A = 1;
        static {
            System.out.println(A);
            A = 2;
            System.out.println(A);
        }
    }

    static class subClass extends parent{
        public static int B = 3;
    }

    public static void main(String[] args) {
        System.out.println(subClass.B);
    }
}
