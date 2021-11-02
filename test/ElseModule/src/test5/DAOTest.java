package test5;

import org.junit.Test;

import java.util.*;

/**
 * User: czc
 * Date: 2021-10-08
 */
public class DAOTest {
    @Test
    public void test(){
        DAO<User> dao = new DAO<>();
        dao.save("1001",new User(1,18,"zookao"));
        dao.save("1002",new User(2,18,"zookao1"));
        dao.save("1003",new User(3,18,"zookao2"));
        List<User> list = dao.list();
        for (User user : list) {
            System.out.println(user);
        }
    }
}

class DAO<T>{
    private HashMap<String,T> map = new HashMap<>();

    public void save(String id,T entity){
        map.put(id,entity);
    }

    public T get(String id){
        return map.get(id);
    }

    public void update(String id,T entity){
        // map.put(id,entity);
        if(map.containsKey(id)){
            map.put(id,entity);
        }
    }

    public List<T> list(){
        // 错误的
        /*Collection<T> values = map.values();
        return (List<T>) values;*/
        // 正确的
        ArrayList<T> list = new ArrayList<>();
        Collection<T> values = map.values();
        for (T value : values) {
            list.add(value);
        }
        return list;
    }

    public void delete(String id){
        map.remove(id);
    }
}

class User{
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && getAge() == user.getAge() && Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAge(), getName());
    }
}
