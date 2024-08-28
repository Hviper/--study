package com.example.flinkcdc.flink;

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

public class CustomSink extends RichSinkFunction<String> {

    @Override
    public void invoke(String value, Context context) throws Exception {
        System.out.println("json->: " + value);
    }
}


