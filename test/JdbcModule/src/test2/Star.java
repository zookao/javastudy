package test2;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * User: czc
 * Date: 2021-10-13
 */
public class Star {
    public int id;
    public String name;
    public BigDecimal salary;
    public Timestamp created_at;
    public Timestamp updated_at;

    public Star() {
    }

    public Star(int id, String name, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Star{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
