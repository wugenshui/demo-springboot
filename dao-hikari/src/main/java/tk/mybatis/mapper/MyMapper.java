package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 基础Mapper
 * 特别注意：该接口不可以被扫描到
 *
 * @author : chenbo
 * @date : 2019-12-22
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
