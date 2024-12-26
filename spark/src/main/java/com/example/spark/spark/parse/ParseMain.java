package com.example.spark.spark.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseMain {
    public static void main(String[] args) {
        Pattern eqSeparatedOpt = Pattern.compile("(--[^=]+)=(.+)");
        String input = "--name=John";

        Matcher matcher = eqSeparatedOpt.matcher(input);
        if (matcher.matches()) {
            String key = matcher.group(1);  // 捕获组 1: --name
            String value = matcher.group(2);  // 捕获组 2: John
            System.out.println("Key: " + key);
            System.out.println("Value: " + value);
        }
    }
}
