package cn.xz.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author xz
 * @ClassName CustomEvent
 * @Description 自定义事件
 * @date 2019/5/30 0030 11:09
 **/
class CustomEvent extends ApplicationEvent {

    // 创建时传入事件源
    public CustomEvent(Object source) {
        super(source);
    }
}
