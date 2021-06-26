package com.chenbo.daomybatisplus.auth.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TypeEnum {
    USER(1, "用户"),
    STAFF(2, "员工"),
    SUPPLIER(3, "供应商");

    /**
     * 对应数据库的数据
     */
    @EnumValue
    private int code;

    private String name;

    TypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 反序列化
     *
     * @param code 数据库对应的值
     * @return 枚举对象
     */
    @JsonCreator
    public static TypeEnum getByCode(@JsonProperty("code") int code) {
        return Arrays.stream(TypeEnum.values()).filter(item -> item.code == code).findFirst().get();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
