package com.chenbo.demo.mock.moco;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.MocoConfig;
import com.github.dreamhead.moco.Runner;
import com.github.dreamhead.moco.WebSocketServer;
import com.github.dreamhead.moco.config.MocoFileRootConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.github.dreamhead.moco.Moco.*;
import static com.github.dreamhead.moco.Runner.runner;

@SpringBootApplication
public class MockMocoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MockMocoApplication.class, args);

        // 增加mock接口
        MocoConfig config = new MocoFileRootConfig("D:\\WorkSpace\\demo-spring-boot\\mock\\mock-moco\\src\\main\\resources\\");
        config.apply("1.json");

        // Http mock
        HttpServer server = httpServer(12306, config);
        server.request(by(uri("/name"))).response(with(text("name")), header("Content-Type", "text/html"));
        server.request(by(uri("/age"))).response("name");
        server.response("all");

        // WebSocket mock
        WebSocketServer websocketServer = server.websocket("/ws");
        websocketServer.request(by("foo")).response("bar");

        Runner runner = runner(server);
        runner.start();
    }
}
