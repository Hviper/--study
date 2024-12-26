package com.example.metrics;

import com.example.metrics.service.RealTimePushGatewayUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class TestMetrics {
    @Test
    public void testMetrics() throws IOException {
        RealTimePushGatewayUtil.pushALl("kiwi","2025-12-01+08:21:12",666);


    }

    @Test
    public void testMetrics22s() throws IOException {
        RealTimePushGatewayUtil.pushALl2("kiwia","2025-12-01+08:21:12");
        RealTimePushGatewayUtil.pushALl2("kiwia","2025-12-01+08:21:12");
    }

    @Test
    public void testDelete() throws IOException {

        RealTimePushGatewayUtil.deleteGroup("kiwia","2025-12-01+08:21:12");
    }




    @Test
    public void testMetrics2() throws IOException {
        RealTimePushGatewayUtil.deleteAll("kiwi","18");
    }

    @Test
    public void testMetrics3() throws IOException {
        RealTimePushGatewayUtil.delete();
    }

    @Test
    public void testMetrics4() throws IOException {
        Random random = new Random();
        int i = random.nextInt(3);
        System.out.println(i);
    }
    @Test
    public void testMetrics5() throws IOException {
        System.out.println(UUID.randomUUID().toString());
    }

    @Test
    public void testMetrics6() throws IOException {
        long timestamp1 = System.currentTimeMillis();  // 当前时间戳

        System.out.println(isWithinOneHour(timestamp1, 1731519258059L));
    }
    // 判断两个时间戳是否在一小时内
    public static boolean isWithinOneHour(long timestamp1, long timestamp2) {
        long difference = Math.abs(timestamp1 - timestamp2);
        long oneHourInMillis = 3600000;  // 1小时 = 3600000 毫秒
        return difference <= oneHourInMillis;
    }

    @Test
    public void testMetrics7() throws IOException {
        // 将时间戳转换为 Date 对象
        Date date = new Date(1731519258059L);

        // 使用 SimpleDateFormat 格式化 Date 对象为字符串
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);
        System.out.println(formattedDate);
    }
    @Test
    public void testMetrics8() throws IOException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(7);
        List<Integer> collect = list.stream().filter(i -> i > 3).collect(Collectors.toList());
        System.out.println(collect);
    }


    public static void main(String[] args) {
        // 测试告警时间
        String alertTime = "2024-11-26+13:14:11";

        // 调用判断方法
        boolean result = isAlertTimeOlderThanTwoDays(alertTime);

        // 输出结果
        System.out.println("是否超过两天: " + result);
    }

    public static boolean isAlertTimeOlderThanTwoDays(String alertTime) {
        // 定义输入时间的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd+HH:mm:ss");

        // 解析输入的告警时间字符串为 LocalDateTime 对象
        LocalDateTime alertDateTime = LocalDateTime.parse(alertTime, formatter);

        // 获取当前时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 计算告警时间和当前时间的差值（天数）
        long daysBetween = ChronoUnit.DAYS.between(alertDateTime, currentDateTime);

        // 如果差值超过两天，返回 true，否则返回 false
        return daysBetween > 2;
    }
}
