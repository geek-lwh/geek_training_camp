package week_09;

import com.lmax.disruptor.EventFactory;

/**
 * @Author: luweihong
 * @Desc: 天花板
 * @Date: 2023/3/21
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {

    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
