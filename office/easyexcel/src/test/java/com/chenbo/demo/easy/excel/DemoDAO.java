package com.chenbo.demo.easy.excel;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-03-08
 */
@Slf4j
public class DemoDAO {

    public void save(List<DemoData> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
    }
}
