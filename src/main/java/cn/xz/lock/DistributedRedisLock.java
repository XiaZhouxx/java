package cn.xz.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author xz
 * @ClassName DistributedRedisLock
 * @Description 分布式 redis 锁
 * @date 2019/5/7 0007 11:44
 **/
@RestController
public class DistributedRedisLock {
    @Autowired
    StringRedisTemplate template;

    @RequestMapping("/seckill")
    public String seckill() {
        // 当前线程唯一标识
        String uuid = UUID.randomUUID().toString();
        Boolean lock = template.opsForValue().setIfAbsent("lock", uuid);
        try{
            if(lock) {
                // 模拟获取库存
                int stock = Integer.parseInt(template.opsForValue().get("stock")) - 1;
                if(stock > 0) {
                    System.out.println("秒杀成功，库存剩余" + stock);
                    template.opsForValue().set("stock",stock + "");
                }else {
                    System.out.println("秒杀失败");
                }
            }else{
                return "秒杀失败";
            }
        }catch(Exception e) {
        }finally {
            // 释放锁 使用用户唯一标识作为判断 防止业务操作时长过久 锁过期 误删其他锁导致锁永久失效
            if(uuid.equals(template.opsForValue().get("lock"))) {
                template.delete("lock");
            }
        }
        return "秒杀成功";
    }
}
