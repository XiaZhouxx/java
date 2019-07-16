package cn.xz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

/**
 * @author xz
 * @ClassName DemoApplication
 * @Description SpringBoot项目启动类
 * @date 2018/12/3 0003 21:22
 **/
// 能兼容使用多个注册中心
@EnableDiscoveryClient // 还有@EnableEurekaClient 但因为使用该注解后续就只能使用Eureka注册中心
@SpringBootApplication
@MapperScan("cn.xz.mapper")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }

    @EventListener
    public void event(ApplicationEvent event) {
        System.out.println(event.getSource());
    }


}
