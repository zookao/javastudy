package test2;

import java.io.*;

/**
 * User: czc
 * Date: 2021-10-09
 */
public class ReadTest2 {
    public static void main(String[] args) {
        String path = "IOModule"+ File.separator+"folder1"+File.separator+"folder2"+File.separator+"2.txt";
        File file = new File(path);
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) != -1){
                baos.write(bytes,0,len);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
