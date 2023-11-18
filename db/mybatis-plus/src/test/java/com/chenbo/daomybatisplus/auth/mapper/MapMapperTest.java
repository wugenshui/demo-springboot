package com.chenbo.daomybatisplus.auth.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenbo.daomybatisplus.auth.entity.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-12-28
 */
@SpringBootTest
public class MapMapperTest {
    @Resource
    private MapMapper mapMapper;

    @Test
    public void list() {
        List<Map> maps = mapMapper.selectList(new QueryWrapper<>());
        System.out.println("maps = " + maps);
    }

    @Test
    public void insert() {
        Map map = new Map();
        map.setCoordinate("POINT(121.366961 25.190049)");
        mapMapper.insert(map);
        System.out.println("map = " + map);
    }
}
