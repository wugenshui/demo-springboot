package com.chenbo.demo.mock.moco;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.github.dreamhead.moco.Moco.httpServer;
import static com.github.dreamhead.moco.Runner.runner;

@SpringBootApplication
public class MockMocoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MockMocoApplication.class, args);

        // 增加mock接口
        HttpServer server = httpServer(12306);
        server.response("foo");
        Runner runner = runner(server);
        runner.start();
    }
}
