package week_04.homework_01.sync;

public class ThreadCount {
    public static void main(String[] args) throws InterruptedException {
        //System.out.println("system："+Thread.currentThread().getThreadGroup().getParent());
        Thread.currentThread().getThreadGroup().getParent().list();

//        System.out.println("main："+Thread.currentThread().getThreadGroup());
//        Thread.currentThread().getThreadGroup().list();
    }
}
