package chapternine.exercise;

import java.lang.reflect.Method;

/**
 * @Author: HB
 * @Description: javaClass执行工具 - 提供给外部调用的入口, 完成前面几个类的组装逻辑, 完成类加载和反射调用.
 * @CreateDate: 15:52 2020/3/17
 */

public class JavaclassExecuter {

    /*
    * 执行外部传过来的代表一个java类的byte数组
    * 1. 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为HackSystem类
    * 2. 适用HotSwapClassLoader加载生成一个Class对象, 每次执行execute()方法都会生成一个新的类加载实例,因此同一个类可以实现重复加载
    * 3. 通过反射调用调用这个Class对象的main方法
    * 4. 如果运行时出现异常, 将异常信息打印到HackSystem中
    * 5. 最后将缓冲区的信息作为方法的结果返回
    * */
    public static String execute(byte[] classByte){
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System","chapternine/exercise/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modiBytes);
        try{
            Method method = clazz.getMethod("main", new Class[] {String[].class });
            method.invoke(null, new String[] { null });
        }catch (Throwable e){
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}
