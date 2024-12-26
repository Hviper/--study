package org.example.controller.test;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTest {
    @Test
    public void test() {
        String input = "**报警时间** : 当地时间 2024-11-14 01:34:18\n\n**环境** : prod\n\n**集群** : 腾讯云\n\n**规则名称** : 各列空值百分比检测\n\n**规则类型** : 强规则\n\n**空间** : nio_ue\n\n**责任人** : frank.ma2\n\n**描述** : \n\n**库表名** : ads.ue_activity_coupon_transfer_record_diff_1d_i\n\n**分区** : datetime='20241113'\n\n**整体结果** : 检查程序运行异常, 无法判断数据质量\n\n\n------------\n**报警条件详情【**条件间的关系为：或**】**\n\n------------\n**检查耗时** : 258秒\n\n";

        // 正则表达式
        String regex = "\\*\\*分区\\*\\* : datetime='(\\d+)'";

//        数据质量探索_Starrocks_etl_time-2-20241113182003541

        // 编译正则表达式并进行匹配
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // 查找并提取匹配的部分
        if (matcher.find()) {
            // 获取匹配到的值
            String result = matcher.group(1);
            System.out.println("提取到的值: " + result);
        } else {
            System.out.println("没有匹配到任何值");
        }


    }
    @Test
    public void test2() {
        System.out.println(1+2+"123");
    }
}
