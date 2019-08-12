package cn.xz.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xz
 * @ClassName RabbitMQConfig
 * @Description 代码创建 队列 交换器
 * @date 2019/8/2 14:59
 **/
@Configuration
public class RabbitMQConfig {
    // 死信队列交换器相关信息
    private static final String DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    private static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    private static final String MESSAGE_TTL = "x-message-ttl";
    private static final String DEAD_QUEUE = "dead_queue";
    private static final String DEAD_EXCHANGE = "dead_exchange";
    private static final String DEAD_ROUTING_KEY = "dead_key";

    /**
     * 普通队列
     * @return
     */
    @Bean
    public Queue queue() {
        Map<String,Object> map = new HashMap<>(2);
        // 指定死信队列 routing key
        map.put(DEAD_LETTER_ROUTING_KEY,DEAD_ROUTING_KEY);
        // 指定死信交换器
        map.put(DEAD_LETTER_EXCHANGE,DEAD_EXCHANGE);

        // 创建队列 指定死信交换器
        Queue q = new Queue("Sb_Queue",true,false,false,map);

        return q;
    }

    /**
     * 直接交换器
     * @return
     */
    @Bean
    public Exchange exchange() {
        // 直接模式交换器
        DirectExchange ex = new DirectExchange("Sb_exchange");
        return ex;
    }

    /**
     * 绑定队列到交换器中。
     * @return
     */
    @Bean
    public Binding binding() {
        // 绑定队列交换器
        return BindingBuilder.bind(queue()).to(exchange()).with("").noargs();
    }
    /**
     * 死信配置 主要配置普通队列中出现
     * 消息被拒绝（basic.reject/ basic.nack）并且requeue=false
     * 消息TTL过期 可以利用TTL作为延迟队列
     * 普通队列(2000ms)过期 发送给死信队列处理 实现延迟队列
     * 队列达到最大长度
     * 此时消息就会被转发到死信队列中
     * 死信队列其实就是普通的队列
     */
    /**
     * 死信队列
     */
    @Bean
    public Queue deadQueue() {
        return new Queue(DEAD_QUEUE);
    }
    /**
     * 死信交换器
     */
    @Bean
    public Exchange deadExchange() {
        return new DirectExchange(DEAD_EXCHANGE);
    }
    /**
     * 绑定死信队列到交换器中
     */
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(DEAD_ROUTING_KEY).noargs();
    }




}
