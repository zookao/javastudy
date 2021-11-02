package test3;

import java.util.TreeSet;

/**
 * User: czc
 * Date: 2021-10-08
 */
public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
        Employee e1 = new Employee("zookao1", 18, new MyDate(2018, 1, 1));
        Employee e3 = new Employee("zookao3", 19, new MyDate(2019, 1, 1));
        Employee e4 = new Employee("zookao4", 20, new MyDate(2020, 1, 1));
        Employee e2 = new Employee("zookao2", 21, new MyDate(2021, 1, 1));
        treeSet.add(e1);
        treeSet.add(e2);
        treeSet.add(e3);
        treeSet.add(e4);
        for (Object o : treeSet) {
            Employee temp = (Employee) o;
            System.out.println(temp);
        }
    }
}

class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}

class Employee implements Comparable {
    private String name;
    private int age;
    private MyDate birthday;

    public Employee() {
    }

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
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

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Employee) {
            if (this == o) {
                return 0;
            } else {
                Employee o1 = (Employee) o;
                return this.name.compareTo(o1.name);
            }
        }
        throw new RuntimeException("对象错误");
    }
}
