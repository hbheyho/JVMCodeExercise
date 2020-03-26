package chapterten;

/**
 * @Author: HB
 * @Description: 自动装箱陷阱
 *               - 包装类的"=="运算在不遇到算术运算符的情况下不会自动拆箱
 *               - 包装类的equals方法不处理数据转型关系
 * @CreateDate: 10:23 2020/3/24
 */

public class AutoBoxingTrap {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(a == d); // false
        System.out.println(e == f); // false
        System.out.println(d == (a + b)); //true
        System.out.println(g == (a + b));  // true

        System.out.println(g.equals(a + b)); // false
        System.out.println(c.equals(a + b)); // true
    }
}
