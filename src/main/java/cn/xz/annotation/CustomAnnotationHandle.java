package cn.xz.annotation;

import java.lang.reflect.Field;

/**
 * @author xz
 * @ClassName CustomAnnotationHandle
 * @Description 自定义注解处理器
 * @date 2019/6/13 0013 21:41
 **/
public class CustomAnnotationHandle {
    public static void outAnnotationInfo(Class clazz){
        // 反射获取所有字段
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            // 判断是否有该注解
            if(field.isAnnotationPresent(CustomAnnotation.class)){
                // 获取自定义注解
                CustomAnnotation annotation = field.getAnnotation(CustomAnnotation.class);
                System.out.println(annotation.name());
            }
        }
    }
}
