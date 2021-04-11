package week_04.hoursework_01.sync;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Runner1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("进入Runner1运行状态——————————" + i);
        }
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.poll();
        concurrentLinkedQueue.size();
        boolean result = Thread.currentThread().isInterrupted();

        boolean result1 = Thread.interrupted(); // 重置状态

        boolean result3 = Thread.currentThread().isInterrupted();

        System.out.println("Runner1.run result ===>" + result);
        System.out.println("Runner1.run result1 ===>" + result1);
        System.out.println("Runner1.run result3 ===>" + result3);

    }

}
