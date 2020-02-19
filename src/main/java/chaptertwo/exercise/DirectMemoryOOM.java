package chaptertwo.exercise;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author: HB
 * @Description:  直接内存溢出异常测试
 *                VM Args: -XX: MaxDirectMemorySize=10M -Xmx20M
 * @CreateDate: 11:19 2020/2/19
 */

public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
