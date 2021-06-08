package com.github.wugenshui.baseutil.baseutil.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 购物车
 *
 * @author : chenbo
 * @date : 2020-06-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCar {
    /**
     * 购买者
     */
    private String buyer;

    /**
     * 商品数量
     */
    private Integer goodCount;

    /**
     * 商品列表
     */
    private List<Goods> Goods;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * transient属性
     */
    private transient String tempName;
}
