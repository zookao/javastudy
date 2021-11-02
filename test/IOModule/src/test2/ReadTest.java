package test2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * User: czc
 * Date: 2021-10-09
 */
public class ReadTest {
    public static void main(String[] args) {
        String path = "IOModule"+ File.separator+"folder1"+File.separator+"folder2"+File.separator+"2.txt";
        File file = new File(path);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            //方式一
            int c = 0;
            while ((c = fileReader.read()) != -1){
                System.out.print((char)c);
            }
            //方式二
            char[] buffer = new char[5];
            int len;
            while ((len = fileReader.read(buffer)) != -1){
                String s = new String(buffer, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
