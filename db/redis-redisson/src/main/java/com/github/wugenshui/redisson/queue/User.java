package com.github.wugenshui.redisson.queue;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : chenbo
 * @date : 2020-06-01
 */
@Data
@Builder
public class User implements Serializable {
    private Long id;
    private String name;
    private int age;
    private LocalDateTime birthday;
}
