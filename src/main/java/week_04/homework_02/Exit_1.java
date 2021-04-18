package week_04.homework_02;

public class Exit_1 {

    public static void main(String[] args) throws InterruptedException {
        AsyncInvokeMethod asyncInvokeMethod = new AsyncInvokeMethod("lwh");
        String result = asyncInvokeMethod.asyncM();
        if (result != null) {
            System.out.println("收到返回值 result => " + result + "准备退出");
        }

        // 调用退出
        System.exit(-1);
        System.out.println("not exit and do something");
        Thread.sleep(5000);
    }
}
