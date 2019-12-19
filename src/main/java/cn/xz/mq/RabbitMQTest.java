package cn.xz.mq;

import com.rabbitmq.client.Channel;
import org.jboss.logging.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xz
 * @ClassName 消费端
 * @Description 测试消息队列 以及消息确认ACK机制
 * @date 2019/7/16 0016 9:27
 **/
@Component
// @RabbitListener(queues = "test")
public class RabbitMQTest {

    Logger logger = Logger.getLogger(RabbitMQTest.class);

    @RabbitHandler
    public void test(String msg,Message message, Channel channel) throws IOException {
        // RabbitMQ ACK消息确认机制
        long tag = message.getMessageProperties().getDeliveryTag();
        try{
            logger.info("消费消息" + msg);
            if(msg.equals("辣鸡")){
                throw new RuntimeException();
            }
            logger.info("消息消费成功");
            // false表示当前收到消息 true表示所有消费者收到了消息
            channel.basicAck(tag,true);
        }catch(Exception e) {
            //丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            logger.error("消息消费失败");

            // ack返回false 并且重新回到队列重发。 注意冥等性
            channel.basicNack(tag,false,true);

            // 拒绝消息
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }


}
