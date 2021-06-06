package com.chenbo.demo.uaa;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : chenbo
 * @date : 2020-04-25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void getDataSourceTest() {
        try {
            val source = dataSource.getConnection();
            log.info("source = " + source);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
