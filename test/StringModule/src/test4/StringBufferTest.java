package test4;

/**
 * User: czc
 * Date: 2021-09-30
 */
public class StringBufferTest {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("你好啊");
        stringBuffer.append("小王");
        stringBuffer.insert(1,"真");
        stringBuffer.delete(1,2);
        stringBuffer.deleteCharAt(1);
        stringBuffer.replace(2,4,"老王");
        System.out.println(stringBuffer);
    }
}
