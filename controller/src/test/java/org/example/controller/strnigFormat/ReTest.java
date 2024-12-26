package org.example.controller.strnigFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReTest {
    public static void main(String[] args) {
        String json = "{\"config\":{\"update_multi\":true},\"i18n_elements\":{\"zh_cn\":[{\"tag\":\"markdown\",\"content\":\"${content}\",\"text_align\":\"left\",\"text_size\":\"normal\"},{\"tag\":\"table\",\"columns\":[{\"data_type\":\"text\",\"name\":\"diff_type\",\"display_name\":\"差异类型\",\"horizontal_align\":\"left\",\"width\":\"auto\"},{\"data_type\":\"text\",\"name\":\"field_name\",\"display_name\":\"字段名\",\"horizontal_align\":\"left\",\"width\":\"auto\"},{\"data_type\":\"number\",\"name\":\"count\",\"display_name\":\"差异数\",\"horizontal_align\":\"right\",\"width\":\"auto\",\"format\":{\"precision\":0}}],\"rows\":${rows},\"row_height\":\"low\",\"header_style\":{\"background_style\":\"none\",\"bold\":true,\"lines\":1},\"page_size\":5},{\"tag\":\"markdown\",\"content\":\"${detail_info}\",\"text_align\":\"left\",\"text_size\":\"normal\"}]},\"i18n_header\":{\"zh_cn\":{\"title\":{\"tag\":\"plain_text\",\"content\":\"${title}\"},\"subtitle\":{\"tag\":\"plain_text\",\"content\":\"${subtitle}\"},\"template\":\"${card_color}\"}}}\\";
        Map<String, String> replacementMap = new HashMap<>();
        replacementMap.put("content", "new_content_value");
        replacementMap.put("rows", "new_rows_value");
        replacementMap.put("detail_info", "new_detail_info_value");
        replacementMap.put("titlse", "new_title_value");
        replacementMap.put("subtitle", "new_subtitle_value");
        replacementMap.put("card_color", "new_card_color_value");
        replacementMap.put("card_colors", "new_card_color_value");

        String replacedJson = replaceVariables(json, replacementMap);
        System.out.println(replacedJson);
    }

    public static String replaceVariables(String input, Map<String, String> replacementMap) {
        // 定义匹配 ${} 格式的正则表达式模式
        Pattern pattern = Pattern.compile("\\$\\{([^}]*)\\}");
        Matcher matcher = pattern.matcher(input);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            // 获取 ${} 中的变量名
            String variable = matcher.group(1);
            String replacement = replacementMap.get(variable);
            if (replacement == null) {
                // 如果在映射中没找到对应的值，可选择保留原变量或者设置一个默认值等处理方式，这里选择保留原变量
                replacement = "${" + variable + "}";
            }else {
                // 对替换值中的双引号进行转义处理
                replacement = replacement.replace("\"", "\\\"");
            }
            try {
                matcher.appendReplacement(result, replacement);

            }catch (Exception e){
                continue;
            }
        }
        matcher.appendTail(result);
        return result.toString();
    }
}