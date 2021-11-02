package plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * User: zookao
 * Date: 2021-10-21
 */
@Intercepts({
        @Signature(type= StatementHandler.class,method = "parameterize",args=java.sql.Statement.class)
})
public class MyPlugin1 implements Interceptor {
    /**
     * 拦截目标对象的目标方法
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("当前拦截的对象："+invocation.getTarget());
        System.out.println("当前拦截的方法："+invocation.getMethod());
        //动态改变sql参数
        Object target = invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        metaObject.setValue("parameterHandler.parameterObject",3);
        //执行目标方法
        Object proceed = invocation.proceed();
        //返回执行后的返回值
        return proceed;
    }

    /**
     * 为目标对象创建一个代理对象
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("将要包装的对象："+target);
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件的配置信息："+properties);
        Interceptor.super.setProperties(properties);
    }
}
