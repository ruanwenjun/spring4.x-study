package com.smart.service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserDao userDao;
    private LoginLogDao loginLogDao;

    /**
     * 用户名密码是否匹配用户
     *
     * @param usarName
     * @param password
     * @return
     */
    public boolean hasMachUser(String usarName, String password) {
        return userDao.getMatchCount(usarName, password) > 0;
    }

    /**
     * 根据用户名查找用户
     *
     * @param userName
     * @return
     */
    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    /**
     * 登陆成功后更新用户积分，更新用户登陆日志
     *
     * @param user
     */
    @Transactional
    public void loginSuccess(User user) {
        // 更新用户积分
        user.setCredits(user.getCredits() + 5);
        // 创建新的登陆日志
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(user.getLastIp());
        loginLog.setUserId(user.getUserId());
        loginLog.setLoginDate(user.getLastVisit());
        loginLogDao.insertLoginLog(loginLog);
        userDao.updateLoginInfo(user);
    }


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
}
