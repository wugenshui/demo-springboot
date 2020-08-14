package com.chenbo.daomybatis.util;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.springframework.util.StringUtils;

/**
 * 日志格式化
 *
 * @author : chenbo
 * @date : 2020-08-14
 */
public class P6spyLogFormat implements MessageFormattingStrategy {
    @Override
    public String formatMessage(final int connectionId, final String now, final long elapsed, final String category, final String prepared, final String sql, final String url) {
        return !StringUtils.isEmpty(sql) ? " Consume Time：" + elapsed + " ms " + now +
                "\n Execute SQL：" + sql.replaceAll("[\\s]+", " ") + "\n" : "";

    }
}
