package week_06.homework1.lua;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class GuavaLimiter {

    public static void main(String[] args) {
        process();
    }

    @SuppressWarnings("all")
    static class Task implements Runnable {
        private int i;
        private RateLimiter limiter;

        public Task(int i, RateLimiter limiter) {
            this.i = i;
            this.limiter = limiter;
        }

        @Override
        public void run() {
            if (limiter != null) {
                // 不可中断的睡眠,等待限流通过
                limiter.acquire();
            }
            System.out.println(System.currentTimeMillis() + "工人" + this.i + "：WORKING...");
        }
    }

    @SuppressWarnings("all")
    public static void process() {
        Long start = System.currentTimeMillis();
        // 2是速率,get,set rate等方法都是sync的 平滑突发限流(SmoothBursty) 令牌桶
        RateLimiter limiter = RateLimiter.create(2);

        // 漏斗 速率2/s,预热3/s
        RateLimiter r = RateLimiter.create(2, 3, TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i, limiter)).start();
        }

        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
