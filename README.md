# juc-demo
java JUC demo

## CountDownLatch
一个线程必须等待其他几个线程都执行完才能继续执行，否则阻塞

await 阻塞需要等待其他线程的线程，直到计数器为0，该线程从阻塞被唤醒

countDown 其他线程执行完毕，计数器减1

此计数器只能使用一次

## CyclicBarrier

让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活


## Semaphore

用于控制并发执行的线程的数量，线程想要向下执行，必须先申请permit，否则阻塞。线程执行完毕，释放permit，一边下一个线程能获取到permit
