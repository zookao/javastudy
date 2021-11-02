package test1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: czc
 * Date: 2021-09-28
 */
public class DateTest {
    public static void main(String[] args) {
        String str = new String("2020-09-08");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(str);
            java.sql.Date sd = new java.sql.Date(date.getTime());
            System.out.println(sd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
