package cn.xz.service;

import cn.xz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author xz
 * @ClassName UserService
 * @Description
 * @date 2018/12/9 0009 11:26
 **/
@Service
public class UserService extends ClassLoader  {
    @Autowired
    UserMapper userMapper;

    /**
     * Spring 事务传播特性
     * Propagation.MANDATORY 当前没有事务则会抛出异常
     * Propagation.REQUIRED 当前没有事务则新建一个事务执行
     * Propagation.REQUIRED 总是会新建一个事务执行，当前有事务互不干扰
     * Propagation.SUPPORTS 默认非事务执行，当前有事务则加入
     * Propagation.NOT_SUPPORTS 默认非事务，当前有事务不会加入
     * Propagation.NEVER 默认非事务 存在事务则抛出异常
     * Propagation.NESTED 没有事务新建事务执行，存在事务则嵌套加入事务
     */
    @Transactional(propagation = Propagation.NESTED)
    public String sayHi(String name) {

        return name;

    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
