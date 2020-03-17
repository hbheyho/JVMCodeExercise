package chaptereight.exercise;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * @Author: HB
 * @Description:
 * @CreateDate: 14:47 2020/3/13
 */

public class ControlDispatch {
    static class GrandFather{
        void thinking(){
            System.out.println("I am grandfather!");
        }
    }

    static class Father extends GrandFather{
        void thinking(){
            System.out.println("I am father!");
        }
    }

    static class Son extends Father{
        void thinking(){
            // 在此调用GrandFather的thinking()方法(不能修改其他部分代码)很难实现的,因为Son类的thinking()方法中根本
            // 无法获取到一个实际类型为GrandFather的对象引用,遂就无法调用 - invokevirtual指令的分派逻辑是固定的,
            // 只能按照方法接收者的实际类型进行分派,这个逻辑完全固化在虚拟机中

            // 但是依靠MethodHandle(方法句柄)就把分派逻辑给予了程序员来进行决定
            try{
                MethodType methodType = MethodType.methodType(void.class);
                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle methodHandle = ((MethodHandles.Lookup) lookupImpl.get(null)).findSpecial(GrandFather.class,
                        "thinking",methodType,GrandFather.class);
                methodHandle.invoke(this);
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GrandFather son = new Son();
        son.thinking();
    }

}
