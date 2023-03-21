package week_09;

import com.lmax.disruptor.EventHandler;

/**
 * @Author: luweihong
 * @Desc: 天花板
 * @Date: 2023/3/21
 */
public class OrderEventConsumer implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent orderEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("consumer:" + Thread.currentThread().getName() + " Event: value=" + orderEvent.toString() + ",sequence=" + sequence + ",endOfBatch=" + endOfBatch);
    }
}
