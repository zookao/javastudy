package test1;

/**
 * User: czc
 * Date: 2021-09-29
 * 自定义枚举类
 */
public class CustomEnum {
    public static void main(String[] args) {
        Season autumn = Season.AUTUMN;
        String seasonDesc = autumn.getSeasonDesc();
        System.out.println(seasonDesc);
    }
}

class Season{
    private final String seasonName;
    private final String seasonDesc;

    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public static final Season SPRING = new Season("春天","生机");
    public static final Season SUMMER = new Season("夏天","炎热");
    public static final Season AUTUMN = new Season("秋天","丰收");
    public static final Season WINTER = new Season("冬天","严寒");

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}