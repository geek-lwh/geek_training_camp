package week_04.hoursework_02;

import java.util.concurrent.Callable;

public class Call implements Callable {

    private String name;

    public Call(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println("这个是异步线程 : " + Thread.currentThread().getName());
        return "hello:" + name;
    }
}
