package chapterten.exercise;

/**
 * @Author: HB
 * @Description: 命名检查测试类
 * @CreateDate: 10:49 2020/3/26
 */

public class BADLY_NAMED_CODE {
    enum colors{
        red, blue, green;
    }

    static final int _FORTY_TWO = 42;

    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE(){
        return;
    }

    public void NOTcamelCASEmethodName(){
        return;
    }
}
