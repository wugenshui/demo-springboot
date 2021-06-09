package com.github.wugenshui.db.flyway.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wugenshui.db.flyway.entity.Bbb;
import com.github.wugenshui.db.flyway.mapper.BbbMapper;
import org.springframework.stereotype.Service;

@Service
public class BbbService extends ServiceImpl<BbbMapper, Bbb> {
}
