package com.smart.service;

import com.smart.dao.LoginLogDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;
import java.util.Date;

@ContextConfiguration("classpath:/smart-context.xml")
@TransactionConfiguration
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    private UserService userService;
    private LoginLogDao loginLogDao;
    // 测试用户是否存在
    @Test
    public void hasMachUser(){
        boolean b1 = userService.hasMachUser("admin","123456");
        System.out.println(b1);
        assert b1;
    }
    @Test
    public void findUserByUserName(){
        User admin = userService.findUserByUserName("admin");
        System.out.println(admin);
    }
    @Test
    @Rollback(false)
    public void loginSuccess(){
        User user = userService.findUserByUserName("admin");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
        //System.out.println(userService.findUserByUserName("admin"));
    }
    @Test
    public void insertLog(){
        User user = new User();
        user.setUserName("admin");
        user.setLastIp("localhost");
        user.setUserId(2);
        user.setLastVisit(new Date());
        //userService.loginSuccess(user);
        // 创建新的登陆日志
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(user.getLastIp());
        loginLog.setUserId(user.getUserId());
        loginLog.setLoginDate(user.getLastVisit());
        loginLogDao.insertLoginLog(loginLog);
    }



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
}
