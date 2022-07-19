package robert.demo.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier Test
 *
 * 循环栅栏
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活
 *
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        TestThread t1 = new TestThread(cyclicBarrier);
        TestThread t2 = new TestThread(cyclicBarrier);
        TestThread t3 = new TestThread(cyclicBarrier);
        new Thread(t1,"t1").start();
        new Thread(t2,"t2").start();
        new Thread(t3,"t3").start();

    }

    public static class TestThread implements Runnable{

        CyclicBarrier cyclicBarrier;

        public TestThread(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    cyclicBarrier.await();
                    doSomething(i);
                    System.out.println(Thread.currentThread().getName()+" end");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doSomething(int i){
            System.out.println(Thread.currentThread().getName()+" "+ i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
