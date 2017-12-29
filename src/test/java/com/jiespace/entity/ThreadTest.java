package com.jiespace.entity;

import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by huangmingjie on 2017/11/3.
 */
public class ThreadTest {
    
    private static void printNumber(String threadName) {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + ":" + i);
        }
    }
    
    //如何让两个线程依次执行
    private static void demo2() {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });
        
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    A.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printNumber("B");
            }
        });
        
        A.start();
        B.start();
    }
    
    //那如何让两个线程按照指定方式有序交叉运行呢？A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3
    private static void demo3() {
        Object object = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("A 1");
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("A 2");
                    System.out.println("A 3");
                }
                
            }
        });
        
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("B 1");
                    System.out.println("B 2");
                    System.out.println("B 3");
                    object.notify();
                }
            }
        });
        
        A.start();
        B.start();
    }
    
    //四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
    private static void demo4() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (char threadName = 'A'; threadName <= 'C'; threadName++) {
            String name = String.valueOf(threadName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(name + " is ready");
                    countDownLatch.countDown();
                }
            }).start();
        }
        
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("D is ready");
            }
        });
        
        B.start();
    }
    
    //三个运动员各自准备，等到三个人都准备好后，再一起跑
    private static void demo5() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        Random random = new Random();
        for (char threadName = 'A'; threadName <= 'C'; threadName++) {
            String name = String.valueOf(threadName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long prepareTime = random.nextInt(10000) + 100;
                    System.out.println(name + " is preparing for time: " + prepareTime);
                    try {
                        Thread.sleep(prepareTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(name + " is prepared, waiting for others");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name + " starts running");
                }
            }).start();
        }
    }
    
    //子线程完成某件任务后，把得到的结果回传给主线程
    private static void demo6() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        ListeningExecutorService service = MoreExecutors.listeningDecorator(executor);
        ListenableFuture listenableFuture = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getInteger();
            }
        });
    
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer result) {
                System.out.println("=====>result: " + result);
            }
    
            @Override
            public void onFailure(Throwable t) {
                System.out.println("失败！");
            }
        });
    }
    
    private static void demo7(){
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getInteger();
            }
        });
        while (true){
            if(future.isDone()){
                try {
                    System.out.println(future.get());
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    System.out.println("shibail");
                } catch (Exception e) {
                    // ignored
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    private static Integer getInteger(){
        System.out.println("Task starts");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = 0;
        for (int i = 0; i <= 100; i++) {
            result += i;
        }
        System.out.println("Task finished and return result");
        return result;
    }
    
    public static void main(String[] args) {
        demo6();
    }
}
