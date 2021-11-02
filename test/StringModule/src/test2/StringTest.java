package test2;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * User: czc
 * Date: 2021-09-28
 */
public class StringTest {
    public static void main(String[] args) {

        String str = "你好1小王";
        System.out.println(str.length());
        System.out.println(str.charAt(3));
        boolean isEmpty = str.isEmpty();
        System.out.println("isEmpty = " + isEmpty);
        String str1 = str.concat("的爸爸");
        System.out.println(str1);
        System.out.println(str.compareTo(str1));

        String str2 = str.substring(2);
        System.out.println("str2 = " + str2);
        
        String str3 = str.replace("a","b");
        System.out.println("str3 = " + str3);
        String str4 = str.replace("小王","小李");
        System.out.println("str4 = " + str4);

        String[] str5 = str.split("\\d");
        System.out.println(Arrays.toString(str5));

        char[] ch = str.toCharArray();
        System.out.println(Arrays.toString(ch));

        byte[] by = str.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(by));
    }
}
