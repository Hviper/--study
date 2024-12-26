package com.example.flinkcdc.flink;

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

public class CustomSink extends RichSinkFunction<String> {

    @Override
    public void invoke(String value, Context context) {
        System.out.println(context);
        System.out.println("json->: " + value);
    }
}


