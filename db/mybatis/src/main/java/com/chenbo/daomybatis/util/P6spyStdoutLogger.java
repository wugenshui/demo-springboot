package com.chenbo.daomybatis.util;

/**
 * 控制台日志输出
 *
 * @author : chenbo
 * @date : 2020-08-14
 */
public class P6spyStdoutLogger extends com.p6spy.engine.spy.appender.StdoutLogger {

    @Override
    public void logText(String text) {
        // 打印红色 SQL 日志
        System.err.println(text);
    }
}
