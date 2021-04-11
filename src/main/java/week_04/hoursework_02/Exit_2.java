package week_04.hoursework_02;

public class Exit_2 {

    public static void main(String[] args) {
        AsyncInvokeMethod asyncInvokeMethod = new AsyncInvokeMethod("lwh");
        String result = asyncInvokeMethod.asyncM();
        if (result != null) {
            System.out.println("收到返回值 result => " + result + "准备退出");
        }

        // 抛出中断异常
        Thread.currentThread().interrupt();
        try {
            Thread.sleep(5000);
            System.out.println("not exit and do something");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
