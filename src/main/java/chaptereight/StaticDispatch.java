package chaptereight;

/**
 * @Author: HB
 * @Description:  方法静态分派 - 静态类型在编译器是已知,依赖静态类型来决定方法执行版本的分派动作.
 *                典型应用就是方法重载,因为方法重载依靠参数的静态类型来进行方法选择.
 * @CreateDate: 9:54 2020/3/12
 */

public class StaticDispatch {
    static abstract class Human{
    }

    static class Man extends Human{
    }

    static class Woman extends Human{
        int a = 1;
    }

    public void sayHello(Human guy){
        System.out.println("Hello, guy!");
    }

    public void sayHello(Man man){
        System.out.println("Hello, man!");
    }

    public void sayHello(Woman woman){
        System.out.println("Hello, woman");
    }

    public static void main(String[] args) {
        /*
        * Human称为变量(man/woman)的静态类型(外观类型), 静态类型的变化仅仅在使用时变化,变量本身的静态类型不会被改变,并且
        * 最终的静态类型在编译期是可知的
        * Man/Woman称为变量(man/woman)的实际类型(运行时类型),其变换在运行期才可确定,编译器并不知道一个对象的实际类型是什么
        * e.g.,
        * // 实际类型变化
        *   Human human = (new Random()).netBoolean() ? new Man() : new Woman();
        * // 静态类型变换
        *   sr.sayHello((Man)human);
        *   sr.sayHello((Woman)human);
        * */
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }
}
