package week_04.hoursework_01.op;

/**
 * sync是对象锁 当前对象锁住后 即便是不同的方法也会堵塞
 */
public class SyncTool {

    public synchronized void a()  {
        System.out.println("我获取了a锁 name : " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void b() {
        System.out.println("我获取了b锁 name : " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
