package zoo;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

//注入的注解，用于指定注入类型
public @interface Inject {
	public String value();
}
