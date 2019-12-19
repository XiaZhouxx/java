package cn.xz;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author xz
 * @ClassName StartTest
 * @Description 实现在容器启动时运行一些代码 还可以实现CommandLineRunner接口实现
 * @date 2019/7/14 0014 17:40
 **/
@Component
public class StartTest implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("Spring boot 容器启动了");
    }

}
