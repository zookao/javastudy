package boot.config;

import boot.bean.School;
import boot.bean.Student;
import boot.bean.Teacher;
import ch.qos.logback.core.db.DBHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Configuration这是一个配置类==配置文件
 * User: zookao
 * Date: 2021-11-15
 * Configuration(proxyBeanMethods = true) 代理bean的方法，导致Bean单实例
 *     设置最佳实践：如果存在注册依赖，则使用true
 *                 不存在注册依赖，则使用false
 */
@Import({DBHelper.class})
@Configuration()
@ConditionalOnBean(name = "school")
@ImportResource("classpath:beans.xml") //主要是为了旧项目迁移，直接从xml的beans配置中导入

//@EnableConfigurationProperties+@ConfigurationProperties与@Component+@ConfigurationProperties功能相同，但是@Component不能使用在第三方组件中，使用有限制
@EnableConfigurationProperties(Teacher.class)
public class MyConfig {
    @Bean("student")
    public Student student(){
        return new Student("zookao",18);
    }

    @Bean
    public School school(){
        return new School("宁阳第二十三中小学");
    }
}
