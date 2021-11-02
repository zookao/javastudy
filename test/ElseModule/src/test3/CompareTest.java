package test3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * User: czc
 * Date: 2021-09-29
 *
 * comparable
 */
public class CompareTest {

    public static void main(String[] args) {
        Goods[] goods = new Goods[4];
        goods[0] = new Goods("LenovoMouse",20);
        goods[1] = new Goods("XiaomiMouse",40);
        goods[2] = new Goods("DellMouse",10);
        goods[3] = new Goods("LogicMouse",20);

        // Arrays.sort(goods);
        Arrays.sort(goods, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                if (o1 instanceof Goods && o2 instanceof Goods){
                    int result = Double.compare(o1.getPrice(),o2.getPrice());
                    if(result == 0){
                        return o1.getName().compareTo(o2.getName());
                    }
                    return result;
                }
                throw new RuntimeException("类型不一致");
            }
        });

        System.out.println(Arrays.toString(goods));
    }
}

class Goods implements Comparable {

    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods){
            Goods goods = (Goods) o;
            int result = Double.compare(this.price,goods.price);
            if(result == 0){
                return this.name.compareTo(goods.name);
            }
            return result;
        }else{
            throw new RuntimeException("传入的类型不一致");
        }
    }
}