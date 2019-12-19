package cn.xz.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xz
 * @ClassName RabbitMQController
 * @Description 充当MQ生产者
 * @date 2019/8/2 17:05
 **/
@RestController
public class RabbitMQController {
    @Autowired
    RabbitMQSend send;


    @RequestMapping("/send/{msg}")
    public void test(@PathVariable String msg) {
        send.send(msg);
    }
}
