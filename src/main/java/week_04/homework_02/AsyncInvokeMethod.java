package week_04.homework_02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncInvokeMethod {

    private String name;

    public AsyncInvokeMethod(String name) {
        this.name = name;
    }

    public String asyncM() {
        System.out.println("这个是主线程 : " + Thread.currentThread().getName());
        Call call = new Call(name);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(call);
        try {
            executorService.shutdown();
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
