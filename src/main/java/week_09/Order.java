package week_09;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: luweihong
 * @Desc: 天花板
 * @Date: 2023/3/20
 */
@Data
public class Order {

    private String orderSerial;

    private BigDecimal price;

    private String skuCode;

    private String unionId;

    private String channelId;

    public static Order newOrder(){
        Order order = new Order();
        order.setOrderSerial(UUID.randomUUID().toString());
        order.setPrice(BigDecimal.valueOf(new Random().nextDouble()));
        order.setUnionId("1231231231");
        order.setSkuCode("1231231231");

        return order;
    }
}
