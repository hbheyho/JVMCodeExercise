package chaptereight;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @Author: HB
 * @Description: MethodHandle - Java.lang.invoke包是在之前单纯依靠符号引用来确定调用的目标方法之外,提供了一种新的动态
 *               确定目标方法的机制, 被称为方法句柄.
 * @CreateDate: 13:09 2020/3/13
 */

public class MethodHandleTest {
    static class ClassA{
        public void println(String s){
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        // 若用object调用println()方法是无法调用的, 因为编译器无法知道object的类型,所以也不能使用符号引用的方法找到对应方法,
        // 运行期才知道
        Object object = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        // 无论object最终是什么类型,最终都能正确调用println方法 - 返回MethodHandle对象
        getPrintlnMH(object).invokeExact("MethodHandleTest");
    }

    /* getPrintlnMH()方法实际上是模拟了invokevirtual指令的执行过程,只不是他的分派逻辑并非固化在Class文件的字节码上,而是由用户
    * 所编写的java方法上 */
    public static MethodHandle getPrintlnMH(Object receiver) throws Throwable{
        // MethodType代表方法类型,方法返回值(第一个参数),具体参数
        MethodType methodType = MethodType.methodType(void.class,String.class);
        // lookup方法来自于MethodHandles类中, 在指定类中查找符合给定方法名称,方法类型和调用权限的方法句柄
        // 这里调用了一个虚方法, 按照java的语法规则,方法第一个参数是隐式的,代表该方法的接受者, 也是this指向的对象,这个参数以前是
        // 放在参数列表中传递,现在提供了bindTo方法来完成
        return MethodHandles.lookup().findVirtual(receiver.getClass(),"println",methodType).bindTo(receiver);
    }

}
