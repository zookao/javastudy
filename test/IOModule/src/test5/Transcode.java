package test5;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 转换编码，从UTF-8转换为GBK
 * User: czc
 * Date: 2021-10-11
 */
public class Transcode {
    public static void main(String[] args) {
        String path2 = "IOModule"+ File.separator+"folder1"+File.separator+"folder2"+File.separator+"2.txt";
        String path4 = "IOModule"+ File.separator+"folder1"+File.separator+"folder2"+File.separator+"4.txt";
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            isr = new InputStreamReader(new FileInputStream(path2), StandardCharsets.UTF_8);
            osw = new OutputStreamWriter(new FileOutputStream(path4),"GBK");
            char[] chars = new char[10];
            int len;
            while((len = isr.read(chars)) != -1){
                osw.write(chars,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(isr != null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw != null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
