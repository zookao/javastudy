package test5;

import java.io.File;

/**
 * User: czc
 * Date: 2021-10-08
 */
public class ExtensionTest {
    public static void main(String[] args) {
        String path = "StringModule"+File.separator+"StringModule.iml";
        File file = new File(path);
        String path1 = file.getPath();
        String[] split = path1.split("\\.");
        if(split.length == 2){
            System.out.println(split[1]);
        }
    }
}
