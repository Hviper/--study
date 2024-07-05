package com.example.mybatis;

import org.junit.jupiter.api.Test;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class TestMain {
    @Test
    public void testSupplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "??????";
            }
        });
        if(future.isDone()){
            System.out.println("完成了");
        }else {
            System.out.println("没有完成");
        }
        String s = future.get();
        System.out.println(s);
    }

    @Test
    public void testNetty() throws InterruptedException {
        EventExecutor executor = new DefaultEventExecutor();

        // Using Promise for asynchronous computation
        Promise<Integer> promise = executor.newPromise();
        executor.execute(() -> {
            try {
                Thread.sleep(2000); // Simulating a delay of 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promise.setSuccess(42);
//            promise.setFailure(new Throwable("错误"));
        });

        // Using Netty Future to handle the asynchronous result
        Future<Integer> future = promise;
        future.addListener((Future<Integer> f) -> {
            if (f.isSuccess()) {
                System.out.println("Result from Netty Future: " + f.get());
            } else {
                System.out.println("Failed to get result: " + f.cause());
            }
        });

        // Wait for the completion of the future
        future.await();

        executor.shutdownGracefully();
    }


    @Test
    public void test2(){
        // CompletableFuture for Task B
        CompletableFuture<Void> taskB = CompletableFuture.runAsync(() -> {
            System.out.println("Task B started");
            // Simulating some computation
            try {
                Thread.sleep(3000); // Simulating a delay of 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task B completed");
        });

        // CompletableFuture for Task C
        CompletableFuture<Void> taskC = CompletableFuture.runAsync(() -> {
            System.out.println("Task C started");
            // Simulating some computation
            try {
                Thread.sleep(2000); // Simulating a delay of 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task C completed");
        });

        // CompletableFuture for Task A, to be triggered when either B or C completes
        CompletableFuture<Void> taskA = CompletableFuture.anyOf(taskB, taskC)
                .thenRun(() -> {
                    System.out.println("Task A started after B or C completes");
                    // Simulating some computation
                    try {
                        Thread.sleep(1000); // Simulating a delay of 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Task A completed");
                });

        // Wait for all tasks to complete
        CompletableFuture.allOf(taskA, taskB, taskC).join();

        System.out.println("All tasks completed");
    }



    @Test
    public void test() throws InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task A started");
            // Simulating some computation
            try {
                Thread.sleep(2000); // Simulating a delay of 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task A completed");
            return "successful";
        });
// 定义B任务 CompletableFuture
        CompletableFuture<String> taskB = stringCompletableFuture.thenApplyAsync((a) -> {
            System.out.println("Task B started after A --->"+a);
            // Simulating some computation
            try {
                Thread.sleep(1000); // Simulating a delay of 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task B completed");
            return "Successful";
        });
        // 定义C任务 CompletableFuture
        CompletableFuture<Void> taskC = stringCompletableFuture.thenApplyAsync((a) -> {
            System.out.println("Task C started after A"+a);
            // Simulating some computation
            try {
                Thread.sleep(1500); // Simulating a delay of 1.5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task C completed");
            return null;
        });
        // 等待所有任务完成
        CompletableFuture.allOf(taskB, taskC).join();

        System.out.println("All tasks completed");
    }

    @Test
    public void test01(){
        // A任务 CompletableFuture
        CompletableFuture<Void> taskA = CompletableFuture.runAsync(() -> {
            System.out.println("Task A started");
            // Simulating some computation
            try {
                Thread.sleep(2000); // Simulating a delay of 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task A completed");
        });

        // 定义B任务 CompletableFuture
        CompletableFuture<Void> taskB = taskA.thenRunAsync(() -> {
            System.out.println("Task B started after A");
            // Simulating some computation
            try {
                Thread.sleep(1000); // Simulating a delay of 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task B completed");
        });

        // 定义C任务 CompletableFuture
        CompletableFuture<Void> taskC = taskA.thenRunAsync(() -> {
            System.out.println("Task C started after A");
            // Simulating some computation
            try {
                Thread.sleep(1500); // Simulating a delay of 1.5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task C completed");
        });

        // 等待所有任务完成
        CompletableFuture.allOf(taskB, taskC).join();


        System.out.println("All tasks completed");
    }
}
