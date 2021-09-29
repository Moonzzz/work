package com.moon.work.thread;

import org.junit.jupiter.api.Test;

import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhen.peng
 * @date 2021-09-29 18:20
 **/
public class ThreadTests {
    @Test
    void test() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        pool.submit(new MyTask(1));
        pool.submit(new MyTask(3));
        pool.submit(new MyTask(2));

        Thread.sleep(5000);
        pool.shutdown();
        pool.awaitTermination(10,TimeUnit.SECONDS);


    }

    static class MyTask implements Runnable {
        static TreeSet<Integer> currentOrder = new TreeSet<>();
        static Semaphore semaphore = new Semaphore(1);

        public MyTask(int order) {
            this.order = order;
            this.runnable = ()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(order);
            };
        }

        private int order;
        private Runnable runnable;

        @Override
        public void run() {
            put(order);
            Integer first;
            do {
                first = currentOrder.first();
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } while (first != order);

            this.runnable.run();
            currentOrder.remove(order);
            semaphore.release();
        }

        synchronized void put(int order){
            currentOrder.add(order);
        }
    }
}
