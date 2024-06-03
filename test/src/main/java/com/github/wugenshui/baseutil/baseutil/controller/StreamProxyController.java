package com.github.wugenshui.baseutil.baseutil.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * EventStream 代理
 *
 * @author : chenbo
 * @date : 2024-06-03
 */
@RestController
@RequestMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class StreamProxyController {
    @GetMapping
    public void test(HttpServletResponse response) throws IOException, InterruptedException {
        if (response.containsHeader("Content-Type")) {
            response.setHeader("Content-Type", "text/event-stream");
        } else {
            response.setHeader("Content-Type", "text/event-stream");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Connection", "keep-alive");
        }
        response.setCharacterEncoding("UTF-8");
        for (int i = 0; i < 10; i++) {
            response.getWriter().write("data:" + new Date() + "\n\n");
            response.getWriter().flush();
            Thread.sleep(500L);
        }
        response.getWriter().close();
    }

}
