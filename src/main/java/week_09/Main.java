package week_09;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Author: luweihong
 * @Desc: 天花板
 * @Date: 2023/3/21
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        long beginTime = System.currentTimeMillis();

        // 定义用于事件处理的线程池，Disruptor 通过 java.util.concurrent.ExecutorService 提供的线程来触发 Consumer 的事件处理
        ThreadFactory threadFactory = r -> new Thread(r, "disruptor");

        // 指定事件工厂
        OrderEventFactory factory = new OrderEventFactory();

        // 指定 ring buffer字节大小，必需为2的N次方(能将求模运算转为位运算提高效率 )，否则影响性能
        int bufferSize = 1024 * 1024;

        // 单线程模式，获取额外的性能
        Disruptor<OrderEvent> disruptor = new Disruptor<>(factory, bufferSize, threadFactory,
                ProducerType.SINGLE, new YieldingWaitStrategy());
        // 设置事件业务处理器---消费者
        disruptor.handleEventsWith(new OrderEventConsumer());
        // 启动disruptor线程
        disruptor.start();

        // 获取 ring buffer环，用于接取生产者生产的事件
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        // 为 ring buffer指定事件生产者
        OrderEventProducer producer = new OrderEventProducer(ringBuffer);
        //LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);

        for (int i = 0; i<100000; i++) {
            Order order = Order.newOrder();
            producer.produceData(order);// 生产者生产数据
        }

        disruptor.shutdown(); //关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；

        long cost = System.currentTimeMillis() - beginTime;
        System.out.println("总共耗时 " + cost + "毫秒");
    }
}
