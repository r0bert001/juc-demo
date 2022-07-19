package robert.demo.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch Test
 *
 * CountDownLatch 线程计数器
 * 一个线程等待其他线程都执行完
 * await阻塞需要等待其他线程的线程，直到计数器为0，该线程从阻塞被唤醒
 * countDown 其他线程执行完毕，计数器建议
 *
 * 举例：一个查询航班的API，需要从东航、南航、国航查询数据并汇总
 * 创建3个线程分别调用不同航空公司的api
 * 主线程在所有数据都没准备好的情况下会被阻塞，直到所有线程执行完毕的到数据
 */
public class CountDownLatchDemo implements Runnable {



    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        TestThread t1 = new TestThread(latch);
        TestThread t2 = new TestThread(latch);
        TestThread t3 = new TestThread(latch);

        new Thread(t1,"t1").start();
        new Thread(t2,"t2").start();
        new Thread(t3,"t3").start();

        new Thread(new CountDownLatchDemo(latch), "t4").start();


    }


    private CountDownLatch latch;

    public CountDownLatchDemo(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println(Thread.currentThread().getName()+" is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static class TestThread implements Runnable{

        private final CountDownLatch latch;

        public TestThread(CountDownLatch latch){
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+" : "+ latch.getCount());
                }
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
