package cn.xz.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xz
 * @ClassName CustomAutoConfiguration
 * @Description 自定义自动装配配置类
 * @date 2019/7/15 0015 16:25
 **/
@ConfigurationProperties("hello")
public class CustomProperties {
    private String name;

    private boolean state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
