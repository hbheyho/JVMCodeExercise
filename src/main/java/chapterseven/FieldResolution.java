package chapterseven;

/**
 * @Author: HB
 * @Description: 同名字段解析实例 - 使用javac编译不通过,出现'The field Sub.A is ambiguous',IDEA可以编译通过
 * @CreateDate: 15:02 2020/3/9
 */

public class FieldResolution {
    interface interface0{
        int A = 0;
    }

    interface interface1 extends interface0{
        int A = 1;
    }

    interface interface2{
        int A = 2;
    }

    static class Parent implements interface1{
        public static int A = 3;
    }

    static class Sub extends Parent implements interface2{
        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }
}
