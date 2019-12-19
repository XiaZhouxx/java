package cn.xz;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

/**
 * @author xz
 * @ClassName DemoApplication
 * @Description SpringBoot项目启动类
 * @date 2018/12/3 0003 21:22
 **/
// 能兼容使用多个注册中心
// @EnableDiscoveryClient // 还有@EnableEurekaClient 但因为使用该注解后续就只能使用Eureka注册中心
@SpringBootApplication
@EnableSwagger2Doc
//@EnableRabbit
@MapperScan("cn.xz.testweb.mapper")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Swagger配置项
     * swagger.title=spring-boot-starter-swagger 标题
     * swagger.description=Starter for swagger 2.x 描述
     * swagger.version=1.4.0.RELEASE 版本
     * swagger.license=Apache License, Version 2.0 许可证
     * swagger.licenseUrl=https://www.apache.org/licenses/LICENSE-2.0.html 许可证url
     * swagger.termsOfServiceUrl=https://github.com/dyc87112/spring-boot-starter-swagger 服务条款
     * swagger.contact.name=didi 维护人
     * swagger.contact.url=http://blog.didispace.com 维护人url
     * swagger.contact.email=dyc87112@qq.com 维护人email
     * swagger.base-package=com.didispace Swagger扫描的基础包 默认全扫描
     * swagger.base-path=/** 需要处理的基础URL规则 默认/**
     */

    @EventListener
    public void event(ApplicationEvent event) {
        System.out.println(event.getSource());
    }

}
