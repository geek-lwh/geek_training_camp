package week_09;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: luweihong
 * @Desc: 天花板
 * @Date: 2023/3/20
 */
@Data
public class OrderEvent  {

    private String orderSerial;

    private BigDecimal price;

    private String skuCode;

    private String unionId;

    private String channelId;
}
