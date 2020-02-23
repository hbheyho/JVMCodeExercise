package chapterthree;

/**
 * @Author: HB
 * @Description:  finalize()方法测试
 *                - 若对象覆盖了finalize方法和未被虚拟机调用过该方法，则判定该对象有必要执行finalize方法，给予对象一个"重生"机会
 *                 将其加入到F-Queue队列中
 *                - 如果对象在finalize方法中重新与引用链中的对象建立了联系，则不对它进行回收，反之GC对其进行回收
 *                - "重生"机会只有一次，因为一个对象的finalize方法只能被调用一次
 * @CreateDate: 15:06 2020/2/19
 */

public class FinalizeEscapeGC {
    // 该引用的对象可作为GC Roots
    public static FinalizeEscapeGC SAVE_HOOK = null;
    public void isAlive(){
        System.out.println("yes, I am still alive！");
    }

    // 重写finalize方法，在该方法中将对象再次加入到引用链中
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed！");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 第一次对象成功"重生"
        SAVE_HOOK = null;
        System.gc();
        // finalize优先级很低，暂停0.5s,以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, I am died！");
        }

        // 因为finalize只能被执行一次，"重生失败"
        SAVE_HOOK = null;
        System.gc();
        // finalize优先级很低，暂停0.5s,以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, I am died！");
        }

    }
}
