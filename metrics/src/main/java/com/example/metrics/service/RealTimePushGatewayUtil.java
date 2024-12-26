package com.example.metrics.service;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;
import lombok.Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Data
public class RealTimePushGatewayUtil {
    static String METRIC_REALTIME_ALERT = "alert_hedongchang";
    public static PushGateway pushGateway = new PushGateway("127.0.0.1:9091");
    //     创建一个Counter类型指标（这里以Counter为例，也可以是Gauge等其他类型）

    public static void delete() throws IOException {
        pushGateway.delete(METRIC_REALTIME_ALERT);
    }


    public static void deleteGroup(String name, String age) throws IOException {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("age", age);
        pushGateway.delete(METRIC_REALTIME_ALERT,hashMap);
    }



    public static void pushALl(String name, String age,int value) throws IOException {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("age", age);
        Counter MetricJobAlertsCounter;
        MetricJobAlertsCounter = Counter.build()
                .name(METRIC_REALTIME_ALERT)
                .help("help info 哈哈哈哈")
                .labelNames("name", "age")
                .create();   //这里不注册，那prometheus就没有指标吧

        MetricJobAlertsCounter.labels(name, age).inc(value);
        pushGateway.pushAdd(MetricJobAlertsCounter, METRIC_REALTIME_ALERT, hashMap);
    }

    public static void pushALl2(String name, String age) throws IOException {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("age", age);
        Counter MetricJobAlertsCounter;
        MetricJobAlertsCounter = Counter.build()
                .name(METRIC_REALTIME_ALERT)
                .help("help info 哈哈哈哈")
                .labelNames("name", "age")
                .create();   //这里不注册，那prometheus就没有指标吧

        MetricJobAlertsCounter.labels(name, age).inc();
        pushGateway.pushAdd(MetricJobAlertsCounter, METRIC_REALTIME_ALERT, hashMap);
    }


    public static void pushALlCount(String name, String age,int value) throws IOException {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("age", age);
        Gauge MetricJobAlertsCounter;
        MetricJobAlertsCounter = Gauge.build()
                .name(METRIC_REALTIME_ALERT)
                .help("help info 哈哈哈哈")
                .labelNames("name", "age")
                .create();   //这里不注册，那prometheus就没有指标吧

        MetricJobAlertsCounter.labels(name, age).inc(value);
        pushGateway.pushAdd(MetricJobAlertsCounter, METRIC_REALTIME_ALERT, hashMap);
    }

    public static void deleteAll(String name, String age) throws IOException {
        Map<String, String> groupingKey = new HashMap<>();
        groupingKey.put("age", age);
        groupingKey.put("job", "alert_hedongchang");
        groupingKey.put("name", "kiwi");

        pushGateway.delete(METRIC_REALTIME_ALERT, groupingKey);
    }


}
