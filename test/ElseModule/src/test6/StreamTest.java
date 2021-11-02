package test6;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * User: czc
 * Date: 2021-10-12
 */
public class StreamTest {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aa");
        strings.add("bb");
        strings.add("cc");

        Stream<Object> objectStream = strings.stream().map(new Function<String, Object>() {
            @Override
            public Object apply(String s) {
                return s.toUpperCase();
            }
        });
        objectStream.forEach(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        });
    }
}
