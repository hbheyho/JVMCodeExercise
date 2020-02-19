package chaptertwo.exercise;

/**
 * @Author: HB
 * @Description:  java 栈内存溢出异常测试
 *                VM Args: -Xss128k
 *                - 设置栈大小为128k（hotspot不区分虚拟机栈和本地栈）
 *                - 栈帧太大导致StackOverflowError(其中的局部变量表过大,导致一个栈所能存放的栈帧变少)
 * @CreateDate: 19:36 2020/2/18
 */

public class JavaVMStackSOF2 {
    private static int stackLength = 0;

    public void stackLeak(){
        long unused1,unused2,unused3,unused4,unused5,unused6,unused7,unused8,unused9,unused10,
             unused11,unused12,unused13,unused14,unused15,unused16,unused17,unused18,unused19,unused20,
             unused21,unused22,unused23,unused24,unused25,unused26,unused27,unused28,unused29,unused30,
             unused31,unused32,unused33,unused34,unused35,unused36,unused37,unused38,unused39,unused40,
             unused41,unused42,unused43,unused44,unused45,unused46,unused47,unused48,unused49,unused50,
             unused51,unused52,unused53,unused54,unused55,unused56,unused57,unused58,unused59,unused60,
             unused61,unused62,unused63,unused64,unused65,unused66,unused67,unused68,unused69,unused70;
        stackLength++;
        stackLeak();
             unused1 = unused2 = unused3 = unused4 = unused5 = unused6= unused7 = unused8 = unused9 = unused10 =
             unused11 = unused12 = unused13 = unused14 = unused15 = unused16 = unused17 = unused18 = unused19 = unused20 =
             unused21 = unused22 = unused23 = unused24 = unused25 = unused26 = unused27 = unused28 = unused29 = unused30 =
             unused31 = unused32 = unused33 = unused34 = unused35 = unused36 = unused37 = unused38 = unused39 = unused40 =
             unused41 = unused42 = unused43 = unused44 = unused45 = unused46 = unused47 = unused48 = unused49 = unused50 =
             unused51 = unused52 = unused53 = unused54 = unused55 = unused56 = unused57 = unused58 = unused59 = unused60 =
             unused61 = unused62 = unused63 = unused64 = unused65 = unused66 = unused67 = unused68 = unused69= unused70 = 0;
    }
    public static void main(String[] args) {
        JavaVMStackSOF2 javaVMStackSOF2 = new JavaVMStackSOF2();
        try {
            javaVMStackSOF2.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length: " + javaVMStackSOF2.stackLength);
            throw e;
        }
    }
}
