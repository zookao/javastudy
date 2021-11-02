package test2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * User: czc
 * Date: 2021-09-28
 */
public class DayuShaiWang {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            if(scanner.hasNextLine()){
                String str = scanner.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    long start = sdf.parse("1990-01-01").getTime();
                    long end = sdf.parse(str).getTime();
                    int interval = (int) (((end - start)/3600000) % 5);
                    if(interval == 1 || interval == 2 || interval == 3){
                        System.out.println("打鱼");
                    }else{
                        System.out.println("晒网");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
