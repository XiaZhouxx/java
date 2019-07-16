package cn.xz.aspect;

import org.aspectj.lang.annotation.*;

/**
 * @author xz
 * @ClassName AspectTest
 * @Description
 * @date 2019/7/15 0015 12:35
 **/
@Aspect
public class AspectTest {

    // 声明一个切入点
    @Pointcut("execution(* cn.xz.aspect.User.*(..))  ")
    public void myPointcut(){}


    // 前置通知
    @Before("myPointcut()")
    public void before(){
        System.out.println("前置通知");
    }

    // 后置通知
    @After("myPointcut()")
    public void after(){

    }

    // 环绕通知
    @Around(value = "myPointcut()")
    public void around(){

    }
}
