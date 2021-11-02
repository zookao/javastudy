package test1;

/**
 * User: czc
 * Date: 2021-09-28
 */
public class StringTest {
    public static void main(String[] args) {
        Str str = new Str();
        str.change(str.str,str.ch);
        System.out.println(str.str);
        System.out.println(str.ch);
    }
}

class Str {
    String str = new String("good");
    char[] ch = new char[]{'t','e','s','t'};

    // public void change(String s,char[] c){//两种结果不一致
    public void change(String str,char[] c){
        str = "hello";
        c[0] = 'b';
    }
}