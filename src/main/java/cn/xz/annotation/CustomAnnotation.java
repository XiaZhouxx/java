package cn.xz.annotation;

import java.lang.annotation.*;

/**
 * @author xz
 * @ClassName CustomAnnotation
 * @Description 自定义注解
 * @date 2019/6/13 0013 21:37
 **/
@Target(ElementType.FIELD) // 可作用的范围
@Documented
@Retention(RetentionPolicy.RUNTIME) // 生命周期 运行时
public @interface CustomAnnotation {
    public int id() default 1;

    public String name();
}
