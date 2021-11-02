package models;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * User: czc
 * Date: 2021-10-13
 */
@Alias("shine")
public class Star {
    public int id;
    public String name;
    public String company;
    public BigDecimal salary;
    public Timestamp createdAt;
    public Timestamp updatedAt;
    public Nation nation;

    public Star() {
    }

    public Star(int id, String name, String company, BigDecimal salary, Timestamp createdAt, Timestamp updatedAt, Nation nation) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nation = nation;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "Star{id=" + id + ", name='" + name + '\'' + ", company='" + company + '\'' + ", salary=" + salary + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", nation=" + nation + '}';
    }
}
