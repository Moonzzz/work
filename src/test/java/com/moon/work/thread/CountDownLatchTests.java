package com.moon.work.thread;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhen.peng
 * @date 2021-10-20 11:50
 **/
public class CountDownLatchTests {
    MyCountDownLatch latch;

    @Test
    void test() throws InterruptedException {
        latch = new MyCountDownLatch(2);
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(this::run);
        pool.submit(this::run);

        boolean res = latch.await(3, TimeUnit.SECONDS);
        System.out.println("main thread done,isTimeout:" + res);
    }

    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":say done");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyCountDownLatch {
        private AtomicInteger count;
        private LinkedList<Thread> linkedList = new LinkedList<>();

        public MyCountDownLatch(int count) {
            this.count = new AtomicInteger(count);
        }

        public void countDown() {
            int num = count.decrementAndGet();
            if (num == 0) {
                for (Thread thread : linkedList) {
                    LockSupport.unpark(thread);
                }
            }
        }

        public boolean await(int timeout, TimeUnit timeUnit) {
            linkedList.add(Thread.currentThread());
            long nanos = timeUnit.toNanos(timeout);
            long deadLine = System.nanoTime() + nanos;
            LockSupport.parkNanos(nanos);

            return System.nanoTime() > deadLine;
        }
    }
}
