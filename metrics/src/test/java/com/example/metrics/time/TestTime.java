package com.example.metrics.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTime {
    // 转换时间格式
    public static String convertDateFormat(String inputDate) {
        try {
            // 定义输入时间格式
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 定义输出时间格式
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss");

            // 解析输入时间字符串
            Date date = inputFormat.parse(inputDate);
            // 格式化为目标格式并返回
            return outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        String inputDate = "2024-12-06 15:30:45";
        String outputDate = convertDateFormat(inputDate);
        System.out.println(outputDate);  // 输出: 2024-12-06+15:30:45
    }
}
