package cn.xz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xz
 * @ClassName DistributeRedisLockController
 * @Description 分布式锁实战
 * @date 2019/7/10 0010 17:47
 **/
@RestController
public class DistributeRedisLockController {

    @Autowired
    StringRedisTemplate template;

    @RequestMapping("/getLock")
    public String lockTest(){





        return "success";
    }
}
