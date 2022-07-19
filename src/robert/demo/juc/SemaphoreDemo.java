package robert.demo.juc;

import java.util.concurrent.Semaphore;

/**
 * Semaphore Test
 * 信号量，控制线程并发执行的个数 (线程能执行必须先获取permits许可证)，执行完成后必须释放，否则新的线程会一直被阻塞
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        TestThread t1 =  new TestThread(semaphore);
        TestThread t2 =  new TestThread(semaphore);
        TestThread t3 =  new TestThread(semaphore);
        TestThread t4 =  new TestThread(semaphore);
        TestThread t5 =  new TestThread(semaphore);

        new Thread(t1,"t1").start();
        new Thread(t2,"t2").start();
        new Thread(t3,"t3").start();
        new Thread(t4,"t4").start();
        new Thread(t5,"t5").start();
    }

    public static class TestThread implements Runnable{

        private Semaphore semaphore ;

        public TestThread(Semaphore semaphore){
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(1);
                for (int i = 0; i < 20; i++) {
                    Thread.sleep(1);
                    System.out.println(Thread.currentThread().getName()+" running "+semaphore.availablePermits());
                }
                semaphore.release(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
