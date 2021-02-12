package com.chenbo.baseutil.hutool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : chenbo
 * @date : 2020-09-28
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class DeepEntity implements Serializable {
    private String name;
}
