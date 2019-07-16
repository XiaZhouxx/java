package cn.xz.listener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author xz
 * @ClassName CustomContext
 * @Description
 * @date 2019/7/15 0015 9:55
 **/
@Component
public class CustomContext implements ApplicationEventPublisherAware {

    ApplicationEventPublisher context;

    public void publishEvent(){
        context.publishEvent(new CustomEvent("hello world"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.context = applicationEventPublisher;
    }
}
