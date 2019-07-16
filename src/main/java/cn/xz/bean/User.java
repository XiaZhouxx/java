package cn.xz.bean;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author xz
 * @ClassName User
 * @Description
 * @date 2018/12/5 0005 22:20
 **/
@Data
public class User implements DisposableBean,BeanPostProcessor,ApplicationContextAware {
    private Integer id;

    private String name;

    private Integer age;

    private boolean sex;

    private ApplicationContext context;

    @Override
    public void destroy() throws Exception {
        System.out.println("Bean被销毁了");
    }

    // IOC在创建Bean时的前置处理
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    // IOC在创建Bean时的后置处理
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    // 这里会把IOC容器传入该方法
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
