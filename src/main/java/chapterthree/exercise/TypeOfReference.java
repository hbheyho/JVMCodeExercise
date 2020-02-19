package chapterthree.exercise;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @Author: HB
 * @Description: 强引用 软引用 弱引用  虚引用
 * @CreateDate: 14:02 2020/2/19
 */

public class TypeOfReference {
    public static void main(String[] args) {
        // ========================= 强引用 ============================== //
        /* 强引用 - 只要某个对象有强引用与之关联,GC就不会回收该对象
         * 使用 referenceTips = null 可以中断强引用, 让GC进行回收*/
        TypeOfReference referenceTips = new TypeOfReference();
        referenceTips = null;

        // ========================= 软引用 ============================== //
        /* 软引用 - 存放有用非必须对象，内存不足时,GC回收
         * 软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被JVM回收，这个软引用就会被加入到与之关联的引用队列中
         * 可用来进行缓存功能实现*/
        SoftReference<String> stringSoftReference = new SoftReference<String>(new String("Hello"));
        // get方法来获取与软引用关联的对象的引用
        System.out.println(stringSoftReference.get());
        // ** 若一个软引用同时存在与一个强引用关联，则也不会进行回收
        SoftReference<TypeOfReference> softReference = new SoftReference<TypeOfReference>(referenceTips);
        System.out.println(softReference.get());

        // ========================= 弱引用 ============================== //
        /* 弱引用 - 存放有用非必须对象，无论内存是否足够，被弱引用关联的对象只能生存到下一次垃圾收集发生为止
         * 弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被JVM回收，这个弱引用就会被加入到与之关联的引用队列中*/
        WeakReference<String> stringWeakReference = new WeakReference<String>(new String("Hello"));
        System.out.println(stringWeakReference.get());
        System.gc();
        System.out.println(stringWeakReference.get());
        // ** 若一个弱引用同时存在与一个强引用关联，则也不会进行回收
        WeakReference<TypeOfReference> weakReference = new WeakReference<TypeOfReference>(referenceTips);
        System.out.println(stringWeakReference.get());
        System.gc();
        System.out.println(stringWeakReference.get());

        // ========================= 虚引用 ============================== //
        /* 虚引用 - 一个对象是否有虚引用存在，都不会对其生存时间造成影响，也无法通过虚引用来获取一个对象的实例
         * 唯一目的是为了能在这个对象被收集器回收时收到一个系统通知，所以虚引用必须和引用队列结合使用
         * 当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会把这个虚引用加入到与之关联的引用队列中
         * 程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收*/
        ReferenceQueue<String> stringReferenceQueue = new ReferenceQueue<String>();
        PhantomReference<String> phantomReference = new PhantomReference<String>(new String("Hello"),stringReferenceQueue);
        System.out.println(phantomReference.get());
        System.out.println(stringReferenceQueue.toString());
    }
}
