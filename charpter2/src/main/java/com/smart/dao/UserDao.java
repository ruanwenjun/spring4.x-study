package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private static final String MATCH_COUNT_SQL = "SELECT COUNT(*) FROM t_user WHERE user_name = ? AND password = ?";

    private static final String QUERY_BY_USERNAME_SQL = "SELECT user_id,credits,last_visit,last_ip FROM t_user WHERE user_name = ?";

    private static final String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?,last_ip=?,credits=? WHERE user_name=?";

    /**
     * 根据用户名和密码查找用户匹配数
     *
     * @param userName
     * @param password
     * @return
     */
    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    /**
     * 根据用户名查找用户
     *
     * @param userName
     * @return
     */
    public User findUserByUserName(final String userName) {
        final User user = new User();
        jdbcTemplate.query(QUERY_BY_USERNAME_SQL, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                user.setUserName(userName);
                user.setUserId(rs.getInt("user_id"));
                user.setCredits(rs.getInt("credits"));
                user.setLastVisit(rs.getDate("last_visit"));
                user.setLastIp(rs.getString("last_ip"));
            }
        });
        return user;
    }

    /**
     * 更新用户登陆信息
     *
     * @param user
     */
    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, user.getLastVisit(), user.getLastIp(), user.getCredits(),
                user.getUserName());
    }


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
