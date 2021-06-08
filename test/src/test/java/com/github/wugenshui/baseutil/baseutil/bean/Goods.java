package com.github.wugenshui.baseutil.baseutil.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品
 *
 * @author : chenbo
 * @date : 2020-06-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private Long price;
}
