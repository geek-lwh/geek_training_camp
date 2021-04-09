package week_04.hoursework_01.op;

public class WaitAndNotify {
    public static void main(String[] args) {
        ProducerAndCustomer producerAndCustomer = new ProducerAndCustomer();
        Thread t1 = new Thread(()->{
            try {
                producerAndCustomer.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1生产者");

        Thread t2 = new Thread(()->{
            try {
                producerAndCustomer.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2消费者");

        t1.start();
        t2.start();
    }
}

class ProducerAndCustomer {

    private final int MAX_COUNT = 20;

    private int productCount = 0;

    public synchronized void producer() throws InterruptedException {
        while (true){
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(10);
            if(productCount >= MAX_COUNT){
                System.out.println("满了 不再生产");
                wait();
            }else{
                productCount++;
            }

            notifyAll();
        }
    }

    public synchronized void customer() throws InterruptedException {
        while (true){
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(10);
            if(productCount <=0){
                System.out.println("货仓已经满了 无法消费");
                wait();
            }else {
                productCount--;
            }

            notifyAll();
        }
    }
}

