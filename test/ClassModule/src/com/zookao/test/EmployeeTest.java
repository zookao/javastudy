package com.zookao.test;

/**
 * User: czc
 * Date: 2021-09-24
 */
public class EmployeeTest {
    public static void main(String[] args) {
        CommonEmployee ce = new CommonEmployee();
        ce.setId(1);
        ce.setName("czc");
        ce.setSalary(520);
        ce.work();

        Manager m = new Manager();
        m.setId(2);
        m.setName("zookao");
        m.setSalary(52.1);
        m.work();
    }
}

abstract class Employee{
    private String name;
    private int id;
    private double salary;

    public void Employee(){

    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public double getSalary(){
        return salary;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }

    public abstract void work();
}

class CommonEmployee extends Employee{

    @Override
    public void work() {
        System.out.println("CommonEmployee.work");
    }
}

class Manager extends Employee{
    private double bonus;

    @Override
    public void work() {
        System.out.println("Manager.work");
    }
}