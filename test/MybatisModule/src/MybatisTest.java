import com.github.pagehelper.PageHelper;
import mappers.*;
import models.Nation;
import models.Star;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User: zookao
 * Date: 2021-10-18
 */
public class MybatisTest {
    /*@Test
    public void test1() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = null;
        try {
            sqlSession = ssf.openSession();
            Object o = sqlSession.selectOne("zookaostar.selectStar", 1);
            System.out.println(o);
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }*/

    @Test
    public void test2() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();
        //创建代理对象，无需实现接口
        StarMapper mapper = sqlSession.getMapper(StarMapper.class);
        Star star = mapper.getStarById(1);
        System.out.println(star);
        sqlSession.close();
    }

    @Test
    public void test3() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();
        //基于注解的实现方式
        StarMapperAnnotation mapper = sqlSession.getMapper(StarMapperAnnotation.class);
        Star star = mapper.getStarById(1);
        System.out.println(star);
        sqlSession.close();
    }

    @Test
    public void test4() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = build.openSession();//不自动提交,true为自动动提交
        StarMapper mapper = sqlSession.getMapper(StarMapper.class);

        //增
        // Star star = new Star(0,"周鸿祎","360", new BigDecimal(3500),null,null);
        // mapper.addStar(star);
        // System.out.println(star.getId());

        //改
        // Star star = new Star(3,"马化腾","腾讯", new BigDecimal(6000),null,null);
        // mapper.updateStar(star);

        //删
        // mapper.deleteStarById(6);

        //多参数
        // Star star = mapper.getStarByNameAndCompany("马化腾", "腾讯");
        // System.out.println(star);

        //POJO、Map、TO
        // HashMap<String,Object> map = new HashMap<>();
        // map.put("id",3);
        // map.put("name","马化腾");
        // Star starByMap = mapper.getStarByMap(map);
        // System.out.println(starByMap);

        // List<Star> stars = mapper.getStarsByNameLike("%马%");
        // for (Star star : stars) {
        //     System.out.println(star);
        // }

        // Map<String, Object> map = mapper.getStarByIdReturnMap(3);
        // System.out.println(map);

        Map<Integer, Star> map = mapper.getStarsMapByNameLikeReturnMap("%马%");
        System.out.println(map);

        sqlSession.commit();
    }

    @Test
    public void test6() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();

        StarMapperPlus mapper = sqlSession.getMapper(StarMapperPlus.class);
        Star star = mapper.getStarById(3);
        System.out.println(star);
    }

    @Test
    public void test7() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();

        StarMapperPlus mapper = sqlSession.getMapper(StarMapperPlus.class);
        Star star = mapper.getStarAndNationById(3);
        System.out.println(star);
        System.out.println(star.getNation());
    }

    @Test
    public void test8() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();

        StarMapperPlus mapper = sqlSession.getMapper(StarMapperPlus.class);
        Star star = mapper.getStarByIdStep(3);
        // System.out.println(star);
        System.out.println(star.getName());
        // System.out.println(star.getNation()); //lazyLoadingEnabled
    }

    @Test
    public void test9() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();

        NationMapper mapper = sqlSession.getMapper(NationMapper.class);
        Nation nation = mapper.getNationByIdPlus(2);
        System.out.println(nation);
        System.out.println(nation.getStars());
    }

    @Test
    public void test10() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();

        NationMapper mapper = sqlSession.getMapper(NationMapper.class);
        Nation nation = mapper.getNationByIdStep(2);
        System.out.println(nation);
        System.out.println(nation.getStars());
    }

    @Test
    public void test11() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();

        StarMapperDynamicSql mapper = sqlSession.getMapper(StarMapperDynamicSql.class);
        Star star = new Star(0, "%马%", null, null, null, null, null);
        List<Star> stars = mapper.getStarsByConditionIf(star);
        for (Star star1 : stars) {
            System.out.println(star1);
        }
    }

    @Test
    public void test12() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();

        StarMapperDynamicSql mapper = sqlSession.getMapper(StarMapperDynamicSql.class);
        Star star = new Star(0, "%马%", null, null, null, null, null);
        List<Star> stars = mapper.getStarsByConditionTrim(star);
        for (Star star1 : stars) {
            System.out.println(star1);
        }
    }

    @Test
    public void test13() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();

        StarMapperDynamicSql mapper = sqlSession.getMapper(StarMapperDynamicSql.class);
        Star star = new Star(0, null, null, null, null, null, null);
        List<Star> stars = mapper.getStarsByConditionChoose(star);
        for (Star star1 : stars) {
            System.out.println(star1);
        }
    }

    @Test
    public void test14() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();

        StarMapperDynamicSql mapper = sqlSession.getMapper(StarMapperDynamicSql.class);
        Star star = new Star(1, "马云2", "阿里", null, null, null, null);
        mapper.updateStar(star);
        sqlSession.commit();
    }

    @Test
    public void test15() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();

        StarMapperDynamicSql mapper = sqlSession.getMapper(StarMapperDynamicSql.class);
        int[] ints = {1, 3, 4, 5};
        List<Integer> collect = Arrays.stream(ints).boxed().collect(Collectors.toList());
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(collect);
        PageHelper.startPage(2,2);
        List<Star> stars = mapper.getStarsByConditionForeach(list);
        for (Star star : stars) {
            System.out.println(star);
        }
    }

    @Test
    public void test16() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();
        // SqlSession sqlSession = ssf.openSession(ExecutorType.BATCH); //批量

        StarMapperDynamicSql mapper = sqlSession.getMapper(StarMapperDynamicSql.class);
        ArrayList<Star> stars = new ArrayList<>();
        stars.add(new Star(0, "贝佐斯", "亚马逊", new BigDecimal(7100), null, null, new Nation(2, "美国")));
        stars.add(new Star(0, "佩奇", "谷歌", new BigDecimal(7200), null, null, new Nation(2, "美国")));
        mapper.addStars(stars);
        sqlSession.commit();
    }

    /**
     * 以及缓存失效的情况
     * 1、sqlsession不同
     * 2、sqlsession相同，但是查询条件不同
     * 3、sqlsession相同，两次之间执行了增删改操作
     * 4、sqlsession相同，但是手动清除了以及缓存（sqlSession.clearCache()）
     *
     * @throws Exception
     */
    @Test
    public void testFirstLevelCache() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();
        StarMapper mapper = sqlSession.getMapper(StarMapper.class);
        Star star = mapper.getStarById(1);
        System.out.println(star);
        Star star1 = mapper.getStarById(1);
        System.out.println(star1);
        System.out.println(star == star1);

        SqlSession sqlSession1 = ssf.openSession();
        StarMapper mapper1 = sqlSession1.getMapper(StarMapper.class);
        Star star3 = mapper1.getStarById(1);
        System.out.println(star3);
        System.out.println(star == star3);
    }

    /**
     * 二级缓存，一个namespace对应一个二级缓存
     * 二级缓存相关设置
     * 1、cacheEnabled，关闭，二级缓存关闭，一级缓存一直可用
     * 2、每个select标签都有一个useCache="true",若为false，二级缓存关闭
     * 3、每个增删改标签flushCache="true",若为false,一级缓存和二级缓存都被清空
     * 4、select标签flushCache="true"，等效于不适用任何缓存
     * 5、sqlSession.clearCache()，只是清除当前session的一级缓存
     *
     * @throws Exception
     */
    @Test
    public void testSecondLevelCache() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession1 = ssf.openSession();
        SqlSession sqlSession2 = ssf.openSession();

        StarMapper mapper1 = sqlSession1.getMapper(StarMapper.class);
        Star star1 = mapper1.getStarById(1);
        sqlSession1.close(); //关闭时才会放入二级缓存，否则使用的是一级缓存
        System.out.println(star1);

        StarMapper mapper2 = sqlSession2.getMapper(StarMapper.class);
        Star star2 = mapper2.getStarById(1);
        sqlSession2.close();
        System.out.println(star2);
    }

    @Test
    public void testPlugin1() {

    }
}
