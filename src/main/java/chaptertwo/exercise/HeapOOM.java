package chaptertwo.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HB
 * @Description: java 堆内存溢出异常测试
 *               VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *               - 其中把-Xms -Xmx设置为同样值避免了堆扩展
 *               - -XX:+HeapDumpOnOutOfMemoryError可以让虚拟机在出现OutOfMemoryError时跳出当前内存堆对快照进行转存
 * @CreateDate: 16:33 2020/2/18
 */

public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> oomObjectList = new ArrayList<OOMObject>();
        while (true){
            oomObjectList.add(new OOMObject());
        }
    }
}
