package chaptertwo.exercise;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: HB
 * @Description:  java 方法区溢出异常测试, 利用CGLib技术不断产生大量动态类来填充方法区
 *                JDK 7中类的类型信息还存在永久代中, JDK 8完全移到元空间，遂该实验需要在JDK 7以下版本实验
 *                 VM Args: -XX：PermSize=10M -XX：MaxPermSize=10M
 * @CreateDate: 10:59 2020/2/19
 */

public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects,
                                        MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(o,objects);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject{
    }
}
