package test3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * User: czc
 * Date: 2021-10-09
 */
public class WriteTest {
    public static void main(String[] args) {
        String path = "IOModule"+ File.separator+"folder1"+File.separator+"folder2"+File.separator+"1.txt";
        File file = new File(path);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file,true);
            fw.write("Hello World\n");
            fw.write("            --zookao");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
