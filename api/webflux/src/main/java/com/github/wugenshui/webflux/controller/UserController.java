package com.github.wugenshui.webflux.controller;

import com.github.wugenshui.webflux.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : chenbo
 * @date : 2022-01-28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private Map<Long, User> map = new HashMap<Long, User>(10);

    @PostConstruct
    public void init() {
        map.put(1L, new User(1L, "admin", "admin"));
        map.put(2L, new User(1L, "admin2", "admin2"));
        map.put(3L, new User(1L, "admin3", "admin3"));
    }

    @GetMapping
    public Flux<User> list() {
        return Flux.fromIterable(map.entrySet().stream().map(Map.Entry::getValue)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable("id") Long id) {
        return Mono.just(map.get(id));
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<String>> save(@RequestBody User user) {
        map.put(user.getUid(), user);
        return Mono.just(new ResponseEntity<>("添加成功", HttpStatus.CREATED));
    }
}
