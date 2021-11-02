package test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: czc
 * Date: 2021-09-30
 *
 * Collection的15个方法
 * add() addAll() remove() removeAll() size() isEmpty() clear()
 * contains() containsAll() retainsAll() equals()
 * hashCode() toArray() iterator() iterator.remove()
 *
 * List
 *
 * add() add(插入) get() set() remove(索引)
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(12);
        arrayList.add(34);
        arrayList.add("ABC");
        arrayList.add(new int[]{1,2,3});
        arrayList.add(new Person("zookao",18));

        arrayList.add(0,56);

        List integerList = Arrays.asList(7, 8, 9);
        arrayList.add(6,integerList); //作为整体
        arrayList.addAll(7,integerList); //分开
        System.out.println(arrayList);

        System.out.println("元素1的值："+arrayList.get(1));
    }
}
class Person{
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
