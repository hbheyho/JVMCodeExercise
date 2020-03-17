package chaptereight;

/**
 * @Author: HB
 * @Description: 方法动态分派 - 运行期根据实际类型确定方法执行版本的分派过程, 根源在于invokevirtual方法的执行逻辑.
 *               因为对于虚方法, 采用invokevirtual指令进行执行, 该指令执行时的第一步找到操作数栈顶的第一个
 *               元素所指向的对象的实际类型,然后再对应实际类型中找到与常量描述符和简单名称都相等的方法进行执行.
 * @CreateDate: 11:00 2020/3/12
 */

public class DynamicDispatch {
    static abstract class Human{
        protected abstract void sayHello();
    }

    static class Man extends Human{

        @Override
        protected void sayHello() {
            System.out.println("Hi, man");
        }
    }

    static class Woman extends Human{

        @Override
        protected void sayHello() {
            System.out.println("Hi, woman");
        }
    }

    public static void main(String[] args) {
        /*
        *  不再单单根据变量的静态类型进行分配方法的执行版本, 需要结合实际类型进行分配
        *  man/woman静态类型为Human, man实际类型为Man, woman实际类型为Woman
        * */
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();

        man = new Woman();
        man.sayHello();
    }

}
