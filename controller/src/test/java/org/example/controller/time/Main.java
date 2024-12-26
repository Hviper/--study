package org.example.controller.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

// 抽象处理者
abstract class DateTimeConverter {
    private DateTimeConverter nextConverter;

    public DateTimeConverter setNextConverter(DateTimeConverter nextConverter) {
        this.nextConverter = nextConverter;
        return nextConverter;
    }

    public LocalDateTime convert(Object time) {
        LocalDateTime result = handle(time);
        if (result == null && nextConverter!= null) {
            return nextConverter.convert(time);
        }
        return result;
    }

    protected abstract LocalDateTime handle(Object time);
}

// 具体处理者，处理日期时间格式字符串
class DateTimeFormatConverter extends DateTimeConverter {
    @Override
    protected LocalDateTime handle(Object time) {
        if (time instanceof String) {
            String strTime = (String) time;
            try {
                // 先尝试按照常见的日期时间格式解析（包含了LocalDateTime默认toString的格式情况）
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss][yyyy-MM-dd'T'HH:mm:ss.SSSSSS]");
                return LocalDateTime.parse(strTime, formatter);
            } catch (Exception e) {
                return null;
            }
        } else if (time instanceof LocalDateTime) {
            // 如果是LocalDateTime类型，将其格式化为字符串再解析回LocalDateTime
            LocalDateTime localDateTime = (LocalDateTime) time;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatted = localDateTime.format(formatter);
            return LocalDateTime.parse(formatted, formatter);
        }
        return null;
    }
}

// 具体处理者，处理时间戳格式字符串
class TimestampConverter extends DateTimeConverter {
    @Override
    protected LocalDateTime handle(Object time) {
        if (time instanceof String) {
            try {
                long timestamp = Long.parseLong((String) time);
                Instant instant = Instant.ofEpochMilli(timestamp);
                return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        // 构建责任链
        DateTimeConverter dateTimeFormatConverter = new DateTimeFormatConverter();
        DateTimeConverter timestampConverter = new TimestampConverter();
        dateTimeFormatConverter.setNextConverter(timestampConverter);

        // 测试转换
        String dateTimeStr = "2024-11-13 21:30:00";
        String timestampStr = "1731508236735";
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime localDateTime1 = dateTimeFormatConverter.convert(dateTimeStr);
        LocalDateTime localDateTime2 = dateTimeFormatConverter.convert(timestampStr);
        LocalDateTime localDateTime3 = dateTimeFormatConverter.convert(now);
        LocalDateTime localDateTime4 = dateTimeFormatConverter.convert(now.toString());

        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);
        System.out.println(localDateTime4);
    }
}