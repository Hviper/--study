package org.example.controller.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MainTest {

    /**
     * 使用最小堆（Min-Heap）
     * 方法：维护一个大小为 5 的最小堆。在遍历所有浮点数时，将每个数插入到堆中。
     * 如果堆的大小超过 5，则移除堆中的最小元素。这样，堆顶始终是当前最大的 5 个数中的最小值，即第 5 大的数。
     */
    @Test
    public void test2() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        try {

        }finally {
            lock.unlock();
        }

        var a = "";
        double[] arr = new double[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        PriorityQueue<Double> queue = new PriorityQueue<>(5);
        for (double v : arr) {
            if(queue.size() < 5){
                queue.offer(v);
            }else{
                queue.poll();
                queue.offer(v);
            }

        }
        System.out.println(queue.peek());
    }


    @Test
    public void test1() {
        ArrayList   list = new ArrayList();
        int[] coins = {1, 2, 5};
        coinChange(coins,11);
    }
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int size = coins.length;
        Arrays.sort(coins);
        int[] dp = new int[Math.max(coins[size - 1], amount) + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 2);
        for (int i = 0; i < size; i++) {
            dp[coins[i]] = 1; // 初始化
        }
        dp[0] = 0;

        System.out.println(Arrays.toString(dp));

        // dp[]
        // dp[i] = dp[i-coin]+1
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < size; j++) {
                if (i - coins[j] < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }








    //行大于列
    //列大于行
    @Test
    public void test() {
        int[][] arr = new int[][]{
                {1,2,3,4},
                {1,2,3,4},
                {1,2,3,4},
                {1,2,3,4}
        };
        int row = arr.length;
        int col = arr[0].length;
        //取元素都是arr[i][j]
        //遍历的时候都是默认i为行，j为列进行遍历
        for(int j=0;j<col;j++){
            for(int i=0;i<j;i++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }


        int[] dp = new int[1];
        Arrays.fill(dp,Integer.MAX_VALUE);
    }

}
