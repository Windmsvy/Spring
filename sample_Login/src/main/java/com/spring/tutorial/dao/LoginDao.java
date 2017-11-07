package com.spring.tutorial.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.spring.tutorial.domain.LoginLog;

@Repository
public class LoginDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String INSERT_LOGIN_LOG_SQL = "INSERT INTO t_login_log(user_id,ip,login_datetime)"
            + "Values(?,?,?)";

    public void insertLoginLog(LoginLog loginLog){
        Object [] args = {loginLog.getLoginLogId(),loginLog.getIp(),loginLog.getLoginDate()};
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,args);
    }
}
