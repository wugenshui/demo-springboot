package com.chenbo.demo.single.best.practice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2020-10-28
 */
@Slf4j
public class DateConverter implements Converter<String, Date> {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIMESTAMP_FORMAT = "^\\d+$";

    private static final String DATETIME_FORMAT_MARK = "-";
    private static final String DATE_FORMAT_MARK = ":";

    private static final Map<String, String> formatMap = new HashMap<>();

    static {
        formatMap.put("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$", "yyyy-MM-dd HH:mm:ss");
        formatMap.put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
        formatMap.put("^\\d{4}-\\d{1,2}-\\d{1,2}T{1}\\d{1,2}:\\d{1,2}:\\d{1,2}[.]\\d{1,3}Z$", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatMap.put("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$", "yyyy-MM-dd HH:mm");
        formatMap.put("^\\d{4}-\\d{1,2}$", "yyyy-MM");
    }


    @Override
    public Date convert(String value) {
        String nullStr = "null";
        if (value == null || value.trim().equals("") || nullStr.equalsIgnoreCase(value)) {
            return null;
        }

        value = value.trim();

        try {
            if (value.matches(TIMESTAMP_FORMAT)) {
                Long lDate = Long.valueOf(value);
                return new Date(lDate);
            } else {
                for (Map.Entry<String, String> entry : formatMap.entrySet()) {
                    String regex = entry.getKey();
                    if (value.matches(regex)) {
                        String format = entry.getValue();
                        SimpleDateFormat formatter = new SimpleDateFormat(format);
                        return formatter.parse(value);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", value));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", value));
    }
}
