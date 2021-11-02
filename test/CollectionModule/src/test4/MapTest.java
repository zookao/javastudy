package test4;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * User: czc
 * Date: 2021-10-09
 */
public class MapTest {
    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            File file = new File("CollectionModule" + File.separator + "1.txt");
            br = new BufferedReader(new FileReader(file));
            bw = new BufferedWriter(new FileWriter(new File(file.getParent(),"2.txt")));
            int c = 0;
            char d;
            HashMap<Character, Integer> map = new HashMap<>();
            while ((c = br.read()) != -1){
                d = (char) c;
                if(map.containsKey(d)){
                    map.put(d,map.get(d)+1);
                }else{
                    map.put(d,1);
                }
            }
            Set<Map.Entry<Character, Integer>> entries = map.entrySet();
            Iterator<Map.Entry<Character, Integer>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<Character, Integer> next = iterator.next();
                bw.write(next.getKey()+":"+next.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
