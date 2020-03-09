package chapterseven;

/**
 * @Author: HB
 * @Description: <clinit>()方法在多线程环境下会被正确地同步加锁, 多个线程初始化一个类,只会有一个线程去执行该类的
 *               <clinit>()方法.且同一个加载器下,一个类型只会初始化一次,即<clinit>()方法只执行一次
 * @CreateDate: 15:48 2020/3/9
 */

public class ClinitDeadLoop {
    static {
        if (true){
            System.out.println(Thread.currentThread() + "init DeadLoopClass");
            while (true){
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                // 初始化ClinitDeadLopp类
                ClinitDeadLoop clinitDeadLoop = new ClinitDeadLoop();
                System.out.println(Thread.currentThread() + "run over");
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}

