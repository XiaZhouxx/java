package cn.xz.mq;

import org.jboss.logging.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author xz
 * @ClassName RabbitMQSend
 * @Description 发送消息 以及发送confirm确认机制
 * @date 2019/7/16 0016 14:05
 **/
@Component
public class RabbitMQSend implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

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

    // 消息成功发送到Exchange 但没有发送到队列时回调接口
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info(MessageFormat.format("消息发送ReturnCallback:{0},{1},{2},{3},{4},{5}", message, replyCode, replyText, exchange, routingKey));
    }
}
