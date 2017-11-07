package com.spring.tutorial.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.tutorial.dao.UserDao;
import com.spring.tutorial.dao.LoginDao;
import com.spring.tutorial.domain.LoginLog;
import com.spring.tutorial.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserDao userDao;
    private LoginDao loginDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    public void setLoginDao(LoginDao loginDao){
        this.loginDao = loginDao;
    }

    public boolean hasMatchUser(String userName,String passWord){
        int matchCount = userDao.getMatchCount(userName,passWord);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    public User findUserByUserId(int userId){
        return userDao.findUserByUserId(userId);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(1 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginDao.insertLoginLog(loginLog);
    }
}
