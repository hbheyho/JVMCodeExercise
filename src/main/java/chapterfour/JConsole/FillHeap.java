package chapterfour.JConsole;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HB
 * @Description: JConsole 内存监控, 用于监视被收集器管理的虚拟机内存
 *               VM Args: -Xms100m -Xmx100m -XX:+UseSerialGC
 * @CreateDate: 17:25 2020/2/27
 */

public class FillHeap {
    // 内存占位符, 一个OOMObject对象大约64kb
    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> oomObjectList = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++){
            // 令曲线变化更加明显
            Thread.sleep(50);
            oomObjectList.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException{
        Thread.sleep(12500);
        System.out.println("===== Test Start =====");
        fillHeap(1000);
        Thread.sleep(12500);
    }
}
