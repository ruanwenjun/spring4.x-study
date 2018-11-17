package com.smart.web;

import com.smart.domain.LoginCommand;
import com.smart.domain.User;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LoginController {
    private UserService userService;

    /**
     * 跳转到login.jsp页面
     *
     * @return
     */
    @RequestMapping(value = "/index.html")
    public String loginPage() {
        return "login";
    }

    /**
     * 处理登陆请求
     *
     * @param request
     * @param loginCommand
     * @return 登陆成功跳转到main.jsp，登陆失败返回到login.jsp
     */
    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        boolean machUser = userService.hasMachUser(loginCommand.getUserName(), loginCommand.getPassword());
        if (!machUser) {
            return new ModelAndView("login", "error", "用户名或密码错误");
        } else {
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
