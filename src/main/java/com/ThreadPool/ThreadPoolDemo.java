package com.ThreadPool;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * 池化技术：事先准备好一些资源，有人要用的时候就来我这里拿，用完之后归还
 * 程序运行本质：占用系统资源，我们需要优化资源的使用，于是演变出了池化技术
 */
public class ThreadPoolDemo {

    /**
     * 线程池的好处：
     * 1.降低资源的消耗
     * 2.提高响应速度
     * 3.方便管理
     * 线程复用，可以控制最大并发数，管理线程
     *
     *  线程池必会 三大方法 七大参数，四种拒绝策略
     *  三大方法：
     *          Executors.newSingleThreadExecutor(); //单个线程
     *         Executors.newFixedThreadPool(5); //创建一个固定的线程池的大小
     *         Executors.newCachedThreadPool(); //可伸缩的，遇强则强，遇弱则弱
     *
     */


    /**
     * Executors 工具类
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 使用线程池创建线程
         */
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        ExecutorService executor = Executors.newCachedThreadPool();
//        try {
//            for (int i = 0; i < 100; i++) {
//                executor.execute(() -> {
//                    System.out.println(Thread.currentThread().getName()+":OK");
//                });
//            }
//        }finally {
//            //关闭线程池
//            executor.shutdown();
//        }

        /**
         * 最大线程到底如何定义
         * 1.CPU密集型
         *   几核的CPU就定义为几 可以保证效率最高
         *      Runtime.getRuntime().availableProcessors(); 获取CPU核心数
         * 2.IO密集型
         *   判断程序中十分消耗IO的线程有多少个
         */

        ExecutorService service = new ThreadPoolExecutor(
                2, //核心线程池大小
                5, //最大核心线程池大小
                3, //超时没有人调用就会释放
                TimeUnit.SECONDS,  //超时时间
                new LinkedBlockingQueue<>(3),//阻塞队列
                Executors.defaultThreadFactory(),  //线程工场 创建线程的
                new CustomRejectedExecutionHandler()); //拒绝策略

        try {
            for (int i = 1; i <= 10; i++) {
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+":OK");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }finally {
            service.shutdown();
        }
    }
    /**
     * public static ExecutorService newSingleThreadExecutor() {
     *         return new FinalizableDelegatedExecutorService
     *             (new ThreadPoolExecutor(1, 1,
     *                                     0L, TimeUnit.MILLISECONDS,
     *                                     new LinkedBlockingQueue<Runnable>()));
     *     }
     *
     *
     *  public static ExecutorService newFixedThreadPool(int nThreads) {
     *         return new ThreadPoolExecutor(nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>());
     *     }
     *
     *
     * public static ExecutorService newCachedThreadPool() {
     *         return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                                       60L, TimeUnit.SECONDS,
     *                                       new SynchronousQueue<Runnable>());
     *     }
     * 本质：ThreadPoolExecutor（）
     *
     *
     * public ThreadPoolExecutor(int corePoolSize,  核心线程池大小
     *                               int maximumPoolSize,  最大核心线程池大小
     *                               long keepAliveTime,   超时没有人调用就会释放
     *                               TimeUnit unit, 超时时间
     *                               BlockingQueue<Runnable> workQueue, 阻塞队列
     *                               ThreadFactory threadFactory, 线程工场 创建线程的
     *                               RejectedExecutionHandler handler  拒绝策略
     *                               ) {
     *         if (corePoolSize < 0 ||
     *             maximumPoolSize <= 0 ||
     *             maximumPoolSize < corePoolSize ||
     *             keepAliveTime < 0)
     *             throw new IllegalArgumentException();
     *         if (workQueue == null || threadFactory == null || handler == null)
     *             throw new NullPointerException();
     *         this.corePoolSize = corePoolSize;
     *         this.maximumPoolSize = maximumPoolSize;
     *         this.workQueue = workQueue;
     *         this.keepAliveTime = unit.toNanos(keepAliveTime);
     *         this.threadFactory = threadFactory;
     *         this.handler = handler;
     *     }
     */


}
