package chaptertwo.exercise;

/**
 * @Author: HB
 * @Description:  创建线程导致内存溢出异常测试
 *                - VM Args: -Xss2m
 *                - 每创建一个线程都要为其分配一个2m的栈空间，若内存不足造成OutOfMemoryError错误
 * @CreateDate: 19:58 2020/2/18
 */

public class JavaVMOOM {
    private void dontStop(){
        while (true){
        }
    }

    // 不断创建线程执行dontStop()方法
    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args)  throws Throwable{
        JavaVMOOM javaVMOOM = new JavaVMOOM();
        javaVMOOM.stackLeakByThread();
    }
}
