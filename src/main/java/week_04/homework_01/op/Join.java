package week_04.homework_01.op;

public class Join {
    public static void main(String[] args) {
        Object o = new Object();
        MyThread myThread = new MyThread("thread1 --");
        myThread.setO(o);
        myThread.start();

        synchronized (o){
            for(int i=0;i<100;i++){
                if(i == 20){
                    try {
//                        o.wait(0);
                        // myThread.join 让myThread等待你这个线程做完,堵塞这里
                        myThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + "-----" + i);
            }
        }
    }
}


class MyThread extends Thread{
    private String name;

    private Object o;

    public void setO(Object o){
        this.o = o;
    }

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run(){
//        synchronized (o){
            for(int i=0;i<100;i++){
                System.out.println(name + i);
            }
//        }
    }
}