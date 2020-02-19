package chaptertwo.exercise;

/**
 * @Author: HB
 * @Description: String::intern()为Native方法，若字符串常量池中已经包含一个等于此String对象的字符串，则返回代表池中这个字符串的
 *               String对象的引用，否则，会将String对象包含的字符串添加到常量池中，并返回此String对象的引用
 *               JDK 6 - 得到两个False, JDK 6中，intern()方法会把首次遇到的字符串实例复制到永久代的字符串常量池进行存储，返回的
 *               也是永久代里面的这个字符串实例的引用，而StringBuilder创建的字符串实例在Java堆中
 *               JDK 7 - 得到一个True, 一个False,JDK 7字符串常量池已经移到Java堆中，intern方法就不需要再拷贝字符串实例到永久代中，
 *               只需要在常量池中记录首次出现的实例引用即可，因此intern方法返回的引用和StringBuilder创建的实例为同一个。
 *               "java"这个字符串在执行StringBuilder.toString()之前就已经出现过，字符串常量池中已经有它的引用，不符合intern()的
 *               "首次遇到"原则，则返回已有的引用，则与StringBuilder创建的实例引用不一致
 * @CreateDate: 10:18 2020/2/19
 */

public class TestOfIntern {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
