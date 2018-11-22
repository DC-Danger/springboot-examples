package com.hz.learnboot.tkmybatis.config;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 定制版MyBatis Mapper插件接口，如需其他接口参考官方文档自行添加。
 *
 * 注意：实体类必须用注解标明Id, 否则抛出异常：
 * - Caused by: tk.mybatis.mapper.MapperException: 继承 selectByIds 方法的实体类[com.hz.learnboot.tkmybatis.domain.UserInfo]中必须只有一个带有 @Id 注解的字段
 *
 * Created by hezhao on 2018-07-13 15:12
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, ConditionMapper<T>, IdsMapper<T> {

}
