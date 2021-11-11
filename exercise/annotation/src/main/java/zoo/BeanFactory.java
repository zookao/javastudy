package zoo;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BeanFactory {
 
    public static <Q> Q getBean(Class<Q> clazz) throws InstantiationException, IllegalAccessException {
    	
    	Q result = null;
    	
    	//请补充
    	//首先产生一个clazz的实例对象
        result = clazz.newInstance();

        //请补充
        //查找所有的成员变量，并遍历
        //如果有成员变量带有Inject注解，请采用反射办法获取到注解的值
        //然后产生注解值所对应的实例对象，并赋值给这个成员变量
        //如果该成员变量是private，需要用反射办法设置为可以访问的
        for (Field f : clazz.getDeclaredFields()) {
            if (f.isAnnotationPresent(Inject.class)) {
                try {
                    Inject annotation = f.getAnnotation(Inject.class);
                    String className = annotation.value();
                    Class<?> aClass = Class.forName(className);
                    Object o = aClass.newInstance();
                    String s = Modifier.toString(f.getModifiers());
                    if(s.contains("private")){
                        f.setAccessible(true);
                    }
                    f.set(result,o);
                } catch (Throwable ex) {
                    System.out.println(ex.getStackTrace());
                }
            }
        }
        
        return result;
    }
}