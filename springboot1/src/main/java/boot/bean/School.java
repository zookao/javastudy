package boot.bean;

/**
 * User: zookao
 * Date: 2021-11-15
 */
public class School {
    private String name;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "School{name='" + name + '\'' + '}';
    }
}
