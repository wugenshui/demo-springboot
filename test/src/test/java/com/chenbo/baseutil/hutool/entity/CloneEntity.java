package com.chenbo.baseutil.hutool.entity;

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
public class CloneEntity implements Serializable {
    private DeepEntity deep;
    private int age;
    private int[] ints;
}
