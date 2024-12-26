package org.example.controller.strnigFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringFormateTest {


    public static void main(String[] args) {
        String json = "{\"mentions_aaa\":[\"caili.song\", \"dongchang.he\"],\"mentions_bbb\":[\"caili.song\"],\"ccc\":[\"caili.song\", \"dongchang.he\"],\"bbb\":\"daffsdafsd\",\"mentions_owner\":\"caili.song\"}";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> receivedMap = objectMapper.readValue(json, new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {});
            Map<String, Object> processedMap = processReceivedMap(receivedMap);
            System.out.println(processedMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> processReceivedMap(Map<String, Object> inputMap) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : inputMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof List) {
                List<?> listValue = (List<?>) value;
                if (listValue.size() > 0 && listValue.get(0) instanceof String) {
                    List<String> stringListValue = (List<String>) listValue;
                    resultMap.put(key, processListValue(key,stringListValue));
                } else {
                    resultMap.put(key, value);
                }
            } else {
                resultMap.put(key, value);
            }
        }
        return resultMap;
    }

    public static String processListValue(String key,List<String> list) {

        return String.join(",", list);
    }
}
