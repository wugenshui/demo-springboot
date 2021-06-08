package com.github.wugenshui.baseutil.baseutil.java.util;

import lombok.Data;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2020-09-15
 */
@SpringBootTest
@Data
public class EnumTest {
    private PizzaStatus status;

    /**
     * 匹萨订单状态
     */
    public enum PizzaStatus {
        ORDERED(0, "已订购"),
        READY(1, "已准备"),
        DELIVERED(2, "已派送");

        private final int code;
        private final String msg;

        PizzaStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public boolean isDeliverable() {
        if (getStatus() == PizzaStatus.READY) {
            return true;
        }
        return false;
    }

    @Test
    public void enumConsoleTest() {
        // ORDERED
        System.out.println(PizzaStatus.ORDERED.name());
        // READY
        System.out.println(PizzaStatus.READY);
        // 1
        System.out.println(PizzaStatus.READY.getCode());
        // 已准备
        System.out.println(PizzaStatus.READY.getMsg());
        // class java.lang.String
        System.out.println(PizzaStatus.DELIVERED.name().getClass());
        // class EnumTest$PizzaStatus
        System.out.println(PizzaStatus.ORDERED.getClass());
    }

    @Test
    public void enumTest() {
        setStatus(PizzaStatus.DELIVERED);
        String currentState;
        switch (status) {
            case ORDERED:
                currentState = "已订购";
                break;
            case READY:
                currentState = "已准备";
                break;
            case DELIVERED:
                currentState = "已派送";
                break;
            default:
                currentState = "未知";
                break;
        }
        System.out.println("currentState = " + currentState);
    }
}
