package week_04.hoursework_01.sync;

import java.time.LocalDateTime;

public class DaemonThread extends Thread {


        public DaemonThread(String name) {
            super(name);
        }

        @Override
        public void run() {

            // Checking whether the thread is Daemon or not
            if (Thread.currentThread().isDaemon()) {
                try {
                    System.out.println(getName() + " is Daemon thread : running " + LocalDateTime.now());
                    // 休眠200s
                    Thread.sleep(200_000);
                    System.out.println(getName() + " is Daemon thread: over " + LocalDateTime.now());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    System.out.println(getName() + " is User thread : running " + LocalDateTime.now());
                    // 休眠5s
                    Thread.sleep(5_000);
                    System.out.println(getName() + " is User thread : over " + LocalDateTime.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {

            System.out.println(Thread.currentThread().getName() + ": running " + LocalDateTime.now());

            DaemonThread t1 = new DaemonThread("t1");
            DaemonThread t2 = new DaemonThread("t2");
            DaemonThread t3 = new DaemonThread("t3");

            // Setting user thread t1 to Daemon
            t1.setDaemon(true);

            // starting first 2 threads
            t1.start();
            t2.start();

            // Setting user thread t3 to Daemon
            t3.setDaemon(true);
            t3.start();

            System.out.println(Thread.currentThread().getName() + ": over " + LocalDateTime.now());
        }
    }
