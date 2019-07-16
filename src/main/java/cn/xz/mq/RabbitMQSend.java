package cn.xz.mq;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xz
 * @ClassName RabbitMQSend
 * @Description 发送消息
 * @date 2019/7/16 0016 14:05
 **/
public class RabbitMQSend implements RabbitTemplate.ConfirmCallback {

    Logger logger = Logger.getLogger(RabbitMQSend.class);

    @Autowired
    RabbitTemplate template;


    // 消息发送确认 保证消息发送端不会丢失消息
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String msg = correlationData.getId();
        if(ack) {
            logger.info("消息发送成功" + msg);
        }else{
            logger.error("消息发送失败");
            // 继续发送
            this.send(msg);
        }
    }

    public void send(String msg) {
        template.setConfirmCallback(this);
        template.convertAndSend("ex_test",msg);
    }


}
