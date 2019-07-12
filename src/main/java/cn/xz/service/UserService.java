package cn.xz.service;

import cn.xz.bean.User;
import cn.xz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xz
 * @ClassName UserService
 * @Description
 * @date 2018/12/9 0009 11:26
 **/
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public String sayHi(String name) {

        return name;

    }
}
