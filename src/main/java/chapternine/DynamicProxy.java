package chapternine;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: HB
 * @Description: 动态代理测试
 * @CreateDate: 11:50 2020/3/16
 */

public class DynamicProxy {
    interface  IHello{
        void sayHello();
        void sayBye();
    }

    static class Hello implements IHello{
        @Override
        public void sayHello() {
            System.out.println("hello world");
        }

        @Override
        public void sayBye() {
            System.out.println("bye bye!");
        }
    }

    static class DynamicProxyTest implements InvocationHandler{
        Object originObj;

        Object bind(Object originObj){
            this.originObj = originObj;
            // 这个方法返回了一个实现了IHello接口中每一个方法, 并代理了new Hello()实例的行为的对象 - 其中会进行
            // 验证, 优化, 缓存,同步, 生成字节码和显示加载等操作
            return Proxy.newProxyInstance(originObj.getClass().getClassLoader(),
                    originObj.getClass().getInterfaces(), this);
        }

        /*
        *  proxy - 生成的动态代理类
        *  Method - 反射的Method对象
        *  args - 方法参数
        * */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            // 对带有指定参数的指定对象调用由此Method对象表示的底层方法
            return method.invoke(originObj,args);
        }
    }

    public static void main(String[] args) {
        // 生成动态代理类 - 通过bind方法返回代理类(形如*$Proxy0的代理类)
        IHello hello = (IHello) new DynamicProxyTest().bind(new Hello());
        // 所生成的动态代理类代理了new Hello()实例的行为(通俗来讲就是动态代理类中有sayHello()方法)
        hello.sayHello();
        // 无论调用动态代理类的哪个方法, 最后实际上都是执行InvocationHandler::invoke()中的代理逻辑
        hello.sayBye();

    }
}
