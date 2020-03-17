package chaptereight;

/**
 * @Author: HB
 * @Description: 字段不参与多态, 因为invokevirtual执行对字段无效
 * @CreateDate: 11:42 2020/3/12
 */

public class FieldHasNoPolymorphic {
    static class Father{
        public int money = 1;
        public Father(){
            money = 2;
            showMeMoney();
        }
        public void showMeMoney(){
            System.out.println("I am father, I have $" + money);
        }
    }

    static class Son extends Father{
        // 类成员变量(实例变量）也会有默认初始化值,和类变量(静态变量)类似
        public int money = 3;
        public Son(){
            money = 4;
            showMeMoney();
        }

        public void showMeMoney(){
            System.out.println("I am Son, I have $" + money);
        }
    }

    public static void main(String[] args) {
        Father gay = new Son();
        System.out.println("This gay has $" + gay.money);
        /* 执行结果：I am Son, I have $0
                    I am Son, I have $4
                    This gay has $2
        *  执行结果分析：Son类在创建的时候,首先会隐式调用Father的构造函数, 此时Father.money = 2, 而Father构造函数中
           的showMeMoney方法调用为虚方法调用,实际执行为Son::showMeMoney()方法,并且方法中的money字段为子类拥有,此时还是为0,
           接着进行调用子类的构造函数,子类的money = 4, 并再次调用Son::showMeMoney()方法, 并且最后gay.money调用的为父类的
           money字段.
        * */
    }
}
