package cn.xz.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xz
 * @ClassName ObjectB
 * @Description 循环依赖问题
 * @date 2019/7/4 0004 21:47
 **/
public class ObjectB{
    @Autowired
    private ObjectA objectA;

}




