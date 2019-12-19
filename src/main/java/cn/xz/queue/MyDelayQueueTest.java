package cn.xz.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author xz
 * @ClassName MyDelayQueueTest
 * @Description 延迟队列
 * @date 2019/11/19 0019 19:46
 **/
public class MyDelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<MyDelayed> d = new DelayQueue<MyDelayed>(){{
            put(new MyDelayed("张三",1000));
            put(new MyDelayed("张三1",2000));
            put(new MyDelayed("张三2",3000));
            put(new MyDelayed("张三3",4000));
            put(new MyDelayed("张三4",5000));
        }};
        Thread.sleep(1000);
        System.out.println(d.poll().name);
        Thread.sleep(1000);
        System.out.println(d.poll().name);
        Thread.sleep(1000);
        System.out.println(d.poll().name);

    }

    static class MyDelayed implements Delayed {

        public MyDelayed(String name, long time) {
            this.name = name;
            this.time = time;
        }

        private String name;

        /**
         * 创建队列的时候就开始计算时间
         */
        private long start = System.currentTimeMillis();
        /**
         * 延迟时间
         */
        private long time ;

        /**
         *  定义过期规则 返回值 <= 0 则过期 代表元素有效可取
         *  使用队列的时间 + 延迟时间 - 取数据的时间 = 0 代表元素到期 可以被获取
         *  这里的时间单位根据实际情况设置(秒、毫秒、微秒)
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert((start + time) - System.currentTimeMillis(),TimeUnit.MICROSECONDS);
        }

        /**
         *  定义过期时间的排序规则 -- 代表取数据时的优先级顺序
          */
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
