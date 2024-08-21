package org.example.hadoopstudy.leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;

public class MainTest {


    @Test
    public void test01(){
        Set<Integer> set = new HashSet<>();
        set.add(11);
        set.add(23);
        set.add(34);
        set.add(49);
        set.add(50);
        Iterator<Integer> iterator = set.iterator();
        iterator.forEachRemaining(System.out::println);
    }
    //  String input = "abc123daddef456adsadghi001dadjkl0001d7890asdmno01asdsdpqr001";
    //  System.out.println(countUniqueNumbers(input));  // 输出: 4
    public static int countUniqueNumbers(String s) {
        // 用正则表达式分隔字符串，提取数字
        String[] parts = s.split("\\D+");  // \\D+ 匹配所有非数字字符
        System.out.println(Arrays.toString(parts));
        // 使用 Set 来存储唯一的数字
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (String part : parts) {
            // 如果 part 是有效的数字串（不为空）
            if (!part.isEmpty()) {
                // 转换为整数，自动去除前导零
                int number = Integer.parseInt(part);
                // 将数字添加到集合中
                uniqueNumbers.add(number);
            }
        }

        // 返回集合的大小，表示不同数字的个数
        return uniqueNumbers.size();
    }

    @Test
    public void test() {
//        int[] prices = {7,1,5,3,6,4};
//        System.out.println(maxProfit(prices));
        int[] nums = {2,3,1,1,0};
        System.out.println(canJump(nums));
    }


    //跳跃游戏

    /**
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int maxJump = nums[0];
        for (int i = 1; i < len; i++) {
            if(maxJump<=0){
                return false;
            }
            if(maxJump-1<nums[i]){
                maxJump = nums[i];
            }else{
                maxJump--;
            }
        }
        return true;
    }

    //[7,1,5,3,6,4]
    //[9,8,7,6,5,4,3,2,1]
    public int maxProfit(int[] prices) {
        int size = prices.length;
        int res = 0;
        int before = prices[0];
        for(int i=1;i<size;i++){
            if(before>prices[i]){
                before = prices[i];
            }else{
                res = Math.max(res,prices[i]-before);
            }
        }
        return res;
    }
}
