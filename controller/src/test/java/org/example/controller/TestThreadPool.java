package org.example.controller;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class TestThreadPool {

    @Test
    public void test7() throws InterruptedException {
        CompletableFuture.runAsync(()->{
            System.out.println("hello");
        });
        Thread.sleep(1000);

//        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2);
//        anyOf.thenAccept(result -> System.out.println("First completed: " + result)); // Task 1 or Task 2
    }

    @Test
    public void test6(){
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务执行");
            return "success";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务执行");
            return "-hdc";
        });
        String join = f1.thenCombine(f2, (i, j) -> i + j).join();
        System.out.println(join);

    }

    @Test
    public void test5() throws InterruptedException {
        //简单优化.................
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("正在执行");
            return "successful ";
        }, Executors.newFixedThreadPool(5)).thenApply(i -> {
            System.out.println("执行成功回调");
            return i + "12";
        });
        Thread.sleep(50000);
    }


    @Test
    public void test4() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "successful";
        }).thenApply(i -> i + "-hdc");
        System.out.println("主线程不会阻塞，因为他只是注册了回调函数.....");
        String join = stringCompletableFuture.join();
        System.out.println(join);
    }

    @Test
    public void testThreadPool() throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(()-> "result");
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(task);
        Future<?> submit = executorService.submit(task);
        String o = (String) submit.get();
        System.out.println(o);
    }

    @Test
    public void test3() {
        // Create an asynchronous computation
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task result";
        });

        // Perform other operations...

        // Non-blocking call to get the result and handle it
        future.thenApply(result -> {
            System.out.println("Result: " + result);
            return result;
        }).exceptionally(ex -> {
            System.err.println("Exception: " + ex.getMessage());
            return null;
        });

        System.out.println("非阻塞？？？？？？");

        // Ensure the main thread waits for the async task to complete
        try {
            future.get(); // This is just to ensure the program waits for the completion in this example
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test() throws ExecutionException, InterruptedException {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(
                () -> {
                    System.out.println("begin=============");
                    Thread.sleep(100);
                    System.out.println("end===============");
                    return null;
                }
        );
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> submit = (Future<Integer>) executorService.submit(integerFutureTask);
        Integer i = submit.get();
        System.out.println(i);

    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {

        CountTask task = new CountTask(1L, 1000000000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        System.out.println(submit.get());
    }
    class CountTask extends RecursiveTask<Long> {
        Long maxCountRange = 100000000l;//最大计算范围
        Long startNum, endNum;
        public CountTask(long startNum, long endNum){
            this.startNum = startNum;
            this.endNum = endNum;
        }
        @Override
        protected Long compute() {
            long range = endNum - startNum;
            long sum = 0;
            if (range >= maxCountRange) {//如果这次计算的范围大于了计算时规定的最大范围，则进行拆分
                //每次拆分时，都拆分成原来任务范围的一半
                //如1-10,则拆分为1-5,6-10
                Long middle = (startNum + endNum) / 2;
                CountTask subTask1 = new CountTask(startNum, middle);
                CountTask subTask2 = new CountTask(middle + 1, endNum);
                //拆分后，执行fork
                subTask1.fork();
                subTask2.fork();

                sum += subTask2.join();
                sum += subTask1.join();
            } else {//在范围内，则进行计算
                for (; startNum <= endNum; startNum++) {
                    sum += startNum;
                }
            }
            return sum;
        }

    }

}
