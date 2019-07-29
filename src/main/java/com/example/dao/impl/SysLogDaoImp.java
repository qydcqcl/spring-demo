package com.example.dao.impl;

import com.example.bean.SysLog;
import com.example.dao.SysLogDaoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author hzq
 * @date 2019/7/28 0028 上午 11:45
 */
@Repository
public class SysLogDaoImp implements SysLogDaoI {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveSysLog(SysLog syslog) {
        String sql = "insert into sys_log(username,operation,time,method,params,ip,create_time) values(:username,:operation,:time,:method,:params,:ip,:createTime)";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        npjt.update(sql, new BeanPropertySqlParameterSource(syslog));
    }
}
