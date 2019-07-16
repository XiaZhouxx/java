package cn.xz.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xz
 * @ClassName CustomAutoConfiguration
 * @Description 自定义自动装配
 * @date 2019/7/15 0015 16:25
 **/
@Configuration
// 该注解会把配置类放入Spring容器中 当然也可以直接在配置类使用Component
@EnableConfigurationProperties(CustomProperties.class)
public class CustomAutoConfiguration {

    @Autowired
    CustomProperties properties;

    /**
     * 然后就可以根据配置创建你自己的bean
     * 并且通过properties方式防止硬编码
     * 最后如何让Spring Boot给我们自动配置呢？
     * 在resources中创建META-INF/spring.factories文件
     * 配置
     * org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
     * 包名.CustomAutoConfiguration
      */

}
