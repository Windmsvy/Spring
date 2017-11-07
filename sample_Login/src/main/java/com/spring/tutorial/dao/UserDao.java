package com.spring.tutorial.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.spring.tutorial.domain.User;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName,String passWord){
        String sqlStr = "SELECT count(*) FROM t_user"
                + " WHERE user_name=? and password=? ";
        return jdbcTemplate.queryForObject(sqlStr, new Object[]{ userName, passWord },Integer.class);
    }

    public User findUserByUserName(final String userName){
        String sqlStr = "SELECT user_id,user_name,credits"
                + " FROM t_user WHERE user_name=? ";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{ userName }, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public User findUserByUserId(final int userId){
        String sqlStr = "SELECT user_id,user_name,credits"
                + "FROM t_user WHERE user_id=? ";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{userId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(userId);
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user){
        String sqlStr = " UPDATE t_user SET " +
                " last_visit=?,last_ip=?,credits=? WHERE user_id =?";
        jdbcTemplate.update(sqlStr,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
    }
}
