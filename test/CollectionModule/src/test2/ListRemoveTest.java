package test2;

import java.util.ArrayList;

/**
 * User: czc
 * Date: 2021-09-30
 */
public class ListRemoveTest {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        arrayList.remove(2);
        System.out.println(arrayList);
    }
}
