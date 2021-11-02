package test2;

/**
 * User: czc
 * Date: 2021-09-29
 * Enum 枚举类
 */
public class EnumTest {
    public static void main(String[] args) {
        Season summer = Season.SUMMER;
        System.out.println(summer);//重写了toString()

        System.out.println("=======");
        Season[] values = Season.values();
        for (Season value : values) {
            System.out.println(value);
        }
        System.out.println("=======");
        Season autumn = Season.valueOf("AUTUMN");
        System.out.println(autumn);
        autumn.show();
    }
}
interface Info{
    public void show();
}
enum Season implements Info{

    SPRING("春天","生机"){
        @Override
        public void show() {
            System.out.println("春天你好");
        }
    },
    SUMMER("夏天","炎热"){
        @Override
        public void show() {
            System.out.println("夏天你好");
        }
    },
    AUTUMN("秋天","丰收"){
        @Override
        public void show() {
            System.out.println("秋天你好");
        }
    },
    WINTER("冬天","寒冷"){
        @Override
        public void show() {
            System.out.println("冬天你好");
        }
    },;

    private final String seasonName;
    private final String seasonDesc;

    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    /*@Override
    public void show() {
        System.out.println("季节");
    }*/
}