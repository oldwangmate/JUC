package com.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Forkjoin JDK1.7
 *  并行执行任务 提高效率，适用于大数据量 分而治之
 *  特点：工作窃取
 *  维护的都是双端队列
 *  使用forkJoin
 *  1.forkJoinPool 通过它执行
 *  2.计算任务 forkJoin.execute(ForkJoinTask task)
 *  3.计算类继承 RecursiveTask
 *
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    /**
     * 求和计算
     * @param args
     */
    private Long start;  //1
    private Long end; //
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start ) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum +=i;
            }
           return sum;
        } else {
            //分支合并结算
            long mid = (start + end) / 2; //中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, mid);
            task1.fork(); //拆分任务 把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(mid + 1, end);
            task2.fork(); //拆分任务 把任务压入线程队列
            return task1.join() + task2.join();
        }
    }
}
