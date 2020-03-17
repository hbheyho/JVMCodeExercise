package chaptereight;

/**
 * @Author: HB
 * @Description:  单分派、多分派演示
 *                方法的接收者与方法的参数统称为宗量, 单分派是根据一个宗量对目标方法进行选择,多分派是根据多个
 *                宗量对目标方法进行选择.
 *                Java语言就目前来说是一门 静态多分派, 动态单分派的语言.
 * @CreateDate: 14:46 2020/3/12
 */

public class SingleandMultiDispatch {
    static class QQ{
    }

    static class _360{
    }

    public static class Father{
        public void hardChoice(QQ qq){
            System.out.println("father choose qq");
        }

        public void hardChoice(_360 _360){
            System.out.println("father choose 360");
        }
    }

    public static class Son extends Father{
        @Override
        public void hardChoice(QQ qq) {
            System.out.println("son choose qq ");
        }

        @Override
        public void hardChoice(_360 _360) {
            System.out.println("son choose 360");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
        /*执行结果： father choose 360
                    son choose qq
        * 结果分析： 编译阶段 - 静态分派过程：此时选择目标方法的标准有1. 静态类型是Father还是Son, 2. 方法参数是QQ还是_360,
                    此时选择结果最终产物是产生两条invokevirtual指令,两条指令的参数分别指向Father::hardChoice(_360),
                    Father::hardChoice(QQ)
                    运行阶段 - 动态分配过程：也是真正执行invokevirtual指令的过程,找到操作数栈顶的第一个元素所指向的
                    对象的实际类型,然后再对应实际类型中找到与常量描述符和简单名称都相等的方法进行执行, 此时唯一能影响
                    方法选择的就是接受者的实际类型是Father还是Son.
        * */
    }
}
