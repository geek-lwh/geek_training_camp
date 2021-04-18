package week_04.homework_01.op;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ConditionTest {

    // 用于生产
    final ReentrantLock putLock = new ReentrantLock();
    final Condition notFull = putLock.newCondition();

    // 用于消费
    final ReentrantLock takeLock = new ReentrantLock();
    final Condition notEmpty = takeLock.newCondition();


    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();

        ProducerAndCustomer2 producerAndCustomer = conditionTest.new ProducerAndCustomer2();
        Thread t1 = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(900);
                    producerAndCustomer.producer();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1生产者");

        Thread t2 = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(800);
                    producerAndCustomer.customer();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2消费者");

        t1.start();
        t2.start();
    }

    public class ProducerAndCustomer2 {

        public ProducerAndCustomer2() {
            super();
        }

        private static final int MAX_COUNT = 20;

        private int productCount = 0;

        public void producer() throws InterruptedException {
            putLock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
                Thread.sleep(10);
                if (productCount >= MAX_COUNT) {
                    System.out.println("满了 不再生产");
                    notFull.await();
                }

                productCount++;
                // 继续唤醒一个等待线程
                notFull.signal();
            } finally {
                putLock.lock();
            }

            signalNotEmpty();
        }

        public void customer() throws InterruptedException {
            takeLock.lockInterruptibly();
            try{
                System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
                Thread.sleep(10);
                if (productCount == 0) {
                    System.out.println("货仓已经满了 无法消费");
                    notEmpty.await();
                }

                productCount--;
                System.out.println("消费一条 current count : " + productCount);
                // 继续唤醒一个等待线程
                notEmpty.signal();

            }finally {
                takeLock.unlock();
            }

            signalNotFull();
        }

        private void signalNotFull() {
            putLock.lock();
            try {
                notFull.signal();
            } finally {
                putLock.unlock();
            }
        }

        private void signalNotEmpty() {
            takeLock.lock();
            try {
                notEmpty.signal();
            } finally {
                takeLock.unlock();
            }
        }

    }

}



