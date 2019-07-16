package cn.xz.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author xz
 * @ClassName CustomListener
 * @Description 自定义事件监听
 * @date 2019/5/30 0030 11:03
 **/
@Component
public class CustomListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        System.out.println(customEvent.getSource());
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("cn.xz.listener");

        context.publishEvent(new CustomEvent("hello,world"));
    }


}

