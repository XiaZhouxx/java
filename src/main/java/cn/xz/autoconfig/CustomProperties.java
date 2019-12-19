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
    /**
     * 是否启用
     */
    private boolean enable;

    private String name;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
