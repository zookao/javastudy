import java.sql.Timestamp;

/**
 * User: zookao
 * Date: 2021-10-15
 */
public class User {
    public long id;
    public String name;
    public int age;
    public String email;
    public long manager_id;
    public Timestamp create_time;

    public User() {
    }

    public User(long id, String name, int age, String email, long manager_id, Timestamp create_time) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.manager_id = manager_id;
        this.create_time = create_time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getManager_id() {
        return manager_id;
    }

    public void setManager_id(long manager_id) {
        this.manager_id = manager_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", manager_id=" + manager_id +
                ", create_time=" + create_time +
                '}';
    }
}
