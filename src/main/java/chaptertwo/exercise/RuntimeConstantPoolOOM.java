package chaptertwo.exercise;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: HB
 * @Description:  运行时常量池内存溢出测试
 *                VM Args: -XX:PermSize=6M -XX:MaxPermSize=6M
 *                - 从JDK 7开始逐步去除永久代，JDK 8完全使用原空间代替，遂需要在JDK 6下进行测试
 *                - JDK 7开始把永久代的字符串常量池移到了Java堆中，遂可以通过-XMs xm 来限制堆的内存大小
 * @CreateDate: 20:17 2020/2/18
 */

public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用set保持常量池的引用，防止Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short范围内足以让6M的PermSize发生OOM错误
        short i = 0;
        while (true){
            set.add(String.valueOf(i++).intern());
        }
    }
}
