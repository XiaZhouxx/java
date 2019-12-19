package cn.xz.testweb.controller;

import cn.xz.test.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * @author xz
 * @ClassName TestController
 * @Description
 * @date 2019/6/6 0006 14:46
 **/
@Api("测试Controller")
@RestController
public class TestController {
    /**
     * 使用信号量控制最大运行线程量
     */
    static Semaphore semaphore = new Semaphore(1);


    @ApiOperation(value = "测试方法",notes = "描述这个测试方法")
    @GetMapping("/test")
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


    @RequestMapping("/save")
    public List send(@RequestBody Map<String,List<User>> user) {
        System.out.println(user.get("list"));
        return user.get("list");
    }

    @RequestMapping("/testError")
    public void exception() {
        throw new RuntimeException("手动抛出异常");
    }
}
