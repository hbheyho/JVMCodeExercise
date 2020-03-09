package chapterseven;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: HB
 * @Description:  测试不同类加载器对Instanceof关键字运算结果的影响
 *                结论：对于每个类,有加载它的加载器和这个类本身共同确定其在java虚拟机中的唯一性
 * @CreateDate: 16:12 2020/3/9
 */

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception{
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (IOException e){
                    throw new ClassNotFoundException(name);
                }
            }
        };
        // 使用自己的类加载器加载ClassLoaderTest类并生成一个新实例
        Object object = myLoader.loadClass("chapterseven.ClassLoaderTest").newInstance();
        System.out.println(object.getClass());
        System.out.println(object instanceof chapterseven.ClassLoaderTest);
    }
}
