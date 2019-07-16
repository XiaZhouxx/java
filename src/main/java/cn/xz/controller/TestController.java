package cn.xz.controller;

import cn.xz.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Semaphore;

/**
 * @author xz
 * @ClassName TestController
 * @Description
 * @date 2019/6/6 0006 14:46
 **/
@RestController
public class TestController {
    /**
     * 使用信号量控制最大运行线程量
     */
    static Semaphore semaphore = new Semaphore(1);


    @RequestMapping("/test")
    public void test() {
        try{
            System.out.println("请求进入");
            // 获取一个线程 如果执行线程达到设置的阈值 则后续线程会阻塞等待有其他线程执行完毕
            semaphore.acquire();
            System.out.println("成功执行");
            Thread.sleep(10000);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();
            System.out.println("执行完毕");
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBean(User.class);
    }
}
