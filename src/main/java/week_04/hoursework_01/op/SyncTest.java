package week_04.hoursework_01.op;

public class SyncTest {



    public static void main(String[] args){
        SyncTool syncTool = new SyncTool();
        Thread t1 = new Thread(()->{
            syncTool.a();
        });
        Thread t2 = new Thread(()->{
            syncTool.b();
        });

        t1.start();
        t2.start();
    }
}
