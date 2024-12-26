package com.example.kafkastarter;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestApp {
    @Test
    public void testApp() {
        String inputText = "**报警时间** : 北京时间 2024-11-13 22:30:46\n**环境** : prod\n**空间** : nio_ue\n**任务id** : 50001385\n**作业状态** : <font color='red'>FAILED</font>\n**作业名称** : [dim_sync_incr_ods_uds_thesis_prod_tag](https://datasight.nioint.com/#/data_ops/realtime_task_ops?id=50001385&target_workspace_name=nio_ue)\n**负责人** : liam.xu\n**Application_ID** : application_1685584293565_7893996\n**Job_ID** : 31e8a9991496c029fde8c2378f84db24\n**监控地址** : [https://monitor.nioint.com/d/wKbnD5Gnk/apache-flink-2021-dashboard-for-job-task-manager?orgId=1&var-Source=promxy&var-Environment=Datasight-Prod&var-PlatformId=50001385&var-jm_instance=All&var-job_name=All&var-task_name=All](https://monitor.nioint.com/d/wKbnD5Gnk/apache-flink-2021-dashboard-for-job-task-manager?orgId=1&var-Source=promxy&var-Environment=Datasight-Prod&var-PlatformId=50001385&var-jm_instance=All&var-job_name=All&var-task_name=All)\n**Flink UI** : [http://p-qcbj6-dd-hadoop-rm-002.prod-qcloud-dd-insight.net:8088/proxy/application_1685584293565_7893996/#/overview](http://p-qcbj6-dd-hadoop-rm-002.prod-qcloud-dd-insight.net:8088/proxy/application_1685584293565_7893996/#/overview)\n**主类** : \n**版本号** : 1.14.3\n";

        // 正则表达式匹配Flink UI后面的URL
        String regex = "\\*\\*Flink UI\\*\\*\\s*:\\s*\\[(http[^\"]+)]";

        // 创建 Pattern 和 Matcher 对象
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputText);

        // 查找并提取URL
        if (matcher.find()) {
            String flinkUIUrl = matcher.group(1);
            System.out.println("Flink UI URL: " + flinkUIUrl);
        } else {
            System.out.println("Flink UI URL not found.");
        }
    }

    @Test
    public void testApp2() {
        List<String> list = Arrays.asList("aaa","bbb");
        System.out.println(list.contains("cc"));

    }
}
