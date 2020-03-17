package chapternine.exercise;

/**
 * @Author: HB
 * @Description: 实现将java.lang.System替换成我们自己定义的HackSystem类
 * @CreateDate: 15:12 2020/3/17
 */

public class ClassModifier {
    // class文件中常量池的起始偏移
    private static final int CONSTANT_POOL_COUNT_INDEX= 8;

    // CONSTANT_UTF8_INFO常量的Tag标志
    private static final int CONSTANT_UTF8_INFO = 1;

    // 常量池中11中常量所占长度
    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5,5};

    private static final int u1 = 1;
    private static final int u2 = 2;

    private byte[] classByte;

    public ClassModifier(byte[] classByte){
        this.classByte = classByte;
    }

    /*
    * 修改常量池中CONSTANT_UTF8_INFO常量的内容
    * @param oldStr 修改前字符串
    *        newStr 修改后字符串
    * @return 返回结果
    * */
    public byte[] modifyUTF8Constant(String oldStr, String newStr){
        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX + u2;
        for (int i = 0; i < cpc; i++){
            int tag = ByteUtils.bytes2Int(classByte, offset, u1);
            if (tag == CONSTANT_UTF8_INFO){
                int len = ByteUtils.bytes2Int(classByte,offset + u1, u2);
                offset += (u1 + u2);
                String str = ByteUtils.bytes2String(classByte, offset, len);
                if (str.equalsIgnoreCase(oldStr)){
                    byte[] strBytes = ByteUtils.String2Bytes(newStr);
                    byte[] strLen = ByteUtils.int2Bytes(newStr.length(), u2);
                    classByte = ByteUtils.bytesReplace(classByte, offset - u2, u2, strLen);
                    classByte = ByteUtils.bytesReplace(classByte, offset, len, strBytes);
                    return classByte;
                } else {
                    offset += len;
                }
            } else {
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }

    /*
    * 获取常量池数量
    * @return 常量池数量
    * */
    public int getConstantPoolCount(){
        return ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }
}
