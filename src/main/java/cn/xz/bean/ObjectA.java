package cn.xz.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xz
 * @ClassName ObjectA
 * @Description
 * @date 2019/7/4 0004 21:46
 **/
public class ObjectA {
    @Autowired
    private ObjectB objectB;
}
