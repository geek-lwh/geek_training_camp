package week_09;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @Author: luweihong
 * @Desc: 天花板
 * @Date: 2023/3/21
 */
public class OrderEventProducer {

    private final RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void produceData(Order order) {
        // 获得下一个Event槽的下标
        ringBuffer.publishEvent(TRANSLATOR, order);

//        long sequence = ringBuffer.next();
//        try {
//            // cas获取这个序号对应的事件
//            OrderEvent event = ringBuffer.get(sequence);
//            BeanUtils.copyProperties(order, event);
//        } finally {
//            ringBuffer.publish(sequence);
//        }
    }

    private static final EventTranslatorOneArg<OrderEvent, Order> TRANSLATOR = (orderEvent, sequeue, order) -> {
        BeanUtils.copyProperties(order, orderEvent);
    };

}
