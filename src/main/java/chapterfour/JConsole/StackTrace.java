package chapterfour.JConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: HB
 * @Description: JConsole 线程监控, 用于查看线程相关信息,造成线程停顿的原因可能有等待外部资源, 死循环和死锁
 * @CreateDate: 10:10 2020/2/28
 */

public class StackTrace {
    /*
    *  线程死循环演示
    * */
    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true);
            }
        },"testBusyThread");
        thread.start();
    }

    /*
    *  线程锁等待演示
    * */
    public static void createLockThread(final Object lock){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        },"testLockThread");
        thread.start();
    }

    /*
     * 线程死锁等待演示
     * */
    static class SynAddRunable implements Runnable{
        int a,b;
        public SynAddRunable(int a, int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 死循环, 锁等待
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        createBusyThread();
        bufferedReader.readLine();
        Object object = new Object();
        createLockThread(object);

        // 死锁
       /* for (int i = 0; i < 100; i++){
            new Thread(new SynAddRunable(1,2)).start();
            new Thread(new SynAddRunable(2,1)).start();
        }*/
    }
}
