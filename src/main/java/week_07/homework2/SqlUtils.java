package week_07.homework2;

public class SqlUtils {


    public static String getSql(int i) {
        return "insert into `order_info` (`order_number`,`buyer_id`,`trade_status`,`pay_status`,`order_amount`,`pay_amount`,`total_amount`,`pay_time`,`create_time`,`delivery_type`) values(" + i + "," +
                i + "," +
                1 + "," +
                2 + "," +
                3 + "," +
                i + "," +
                i + "," +
                "'2021-05-02 20:51:09'" + "," +
                "'2021-05-02 20:51:09'" + "," +
                1 + ")";
    }


//    public static String now(){
//        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return simpleDate.format(new Date());
//    }

}
