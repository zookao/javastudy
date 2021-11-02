package models;

import java.io.Serializable;
import java.util.List;

/**
 * User: zookao
 * Date: 2021-10-19
 */
public class Nation implements Serializable {
    public long id;
    public String nation;
    public List<Star> stars;

    public Nation() {
    }

    public Nation(long id, String nation) {
        this.id = id;
        this.nation = nation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Nation{id=" + id + ", nation='" + nation + '\'' + '}';
    }
}
