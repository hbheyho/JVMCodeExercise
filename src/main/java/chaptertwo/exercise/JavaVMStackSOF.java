package chaptertwo.exercise;

/**
 * @Author: HB
 * @Description:  java 栈内存溢出异常测试
 *                VM Args: -Xss128k
 *                - 设置栈大小为128k（hotspot不区分虚拟机栈和本地栈）
 *                - 虚拟机栈容量太小导致StackOverflowError错误
 * @CreateDate: 18:00 2020/2/18
 */

public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }
    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length: " + javaVMStackSOF.stackLength);
            throw e;
        }
    }
}
