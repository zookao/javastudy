package test4;

import java.io.*;

/**
 * User: czc
 * Date: 2021-10-09
 */
public class BufferTest {
    public static void main(String[] args) {
        String path1 = "IOModule"+ File.separator+"folder1"+File.separator+"folder2"+File.separator+"1.txt";
        String path3 = "IOModule"+ File.separator+"folder1"+File.separator+"folder2"+File.separator+"3.txt";
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File(path1)));
            bw = new BufferedWriter(new FileWriter(new File(path3)));
            //方式一
            // char[] chars = new char[1024];
            // int len;
            // while ((len = br.read(chars)) != -1){
            //     bw.write(chars,0,len);
            // }
            //方式二
            String data;
            while ((data = br.readLine()) != null){
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
