package chapternine.exercise;

/**
 * @Author: HB
 * @Description:  为了多次载入执行类而加入的加载器, 把defineClass方法开放出来,只有外部显式调用的
 *                时候才会使用到loadByte方法, 由虚拟机调用时, 仍然按照原来的双亲委派规则使用loadClass进行加载
 *
 * @CreateDate: 15:01 2020/3/17
 */

public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader(){
        super(HotSwapClassLoader.class.getClassLoader());
    }
    /*
    *  将提交执行的java类的byte数组转变为Class对象
    * */
    public Class loadByte(byte[] classByte){
        return defineClass(null,classByte,0,classByte.length);
    }
}
