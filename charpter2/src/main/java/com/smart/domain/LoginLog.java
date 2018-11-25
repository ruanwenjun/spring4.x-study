package com.smart.domain;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {
    private int LogininLogId;
    private int userId;
    private String Ip;
    private Date loginDate;

    public int getLogininLogId() {
        return LogininLogId;
    }

    public void setLogininLogId(int logininLogId) {
        LogininLogId = logininLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
