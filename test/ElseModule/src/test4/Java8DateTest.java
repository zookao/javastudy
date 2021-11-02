package test4;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Set;

/**
 * User: czc
 * Date: 2021-09-29
 */
public class Java8DateTest {
    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        TemporalAccessor temporalAccessor = df.parse("2017-08-16");
        LocalDate localDate = LocalDate.from(temporalAccessor);
        java.sql.Date sd = java.sql.Date.valueOf(localDate);
        System.out.println(sd);
    }

    @Test
    public void test1(){
        //getAvailableZoneIds():获取所的ZoneId
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for(String s : zoneIds){
            System.out.println(s);
        }
        System.out.println();

        //获取“Asia/Tokyo”时区对应的时间
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String format = localDateTime.format(dateTimeFormatter);
        System.out.println(format);
    }
}
