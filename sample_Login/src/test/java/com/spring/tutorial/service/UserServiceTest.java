package com.spring.tutorial.service;

import java.util.Date;
import com.spring.tutorial.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.*;
import static org.testng.Assert.*;

@ContextConfiguration("classpath*:/smart-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Test
    public void hasMatchedUser(){
        boolean b1 = userService.hasMatchUser("admin","123456");
        boolean b2 = userService.hasMatchUser("admin1","12345");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void findUserByUserName() throws Exception{
        for(int i = 0; i < 50; i++){
            User user = userService.findUserByUserName("admin");
            assertEquals(user.getUserName(),"admin");
        }
    }
    @Test
    public void addLoginLog(){
        User user = userService.findUserByUserName("admin");
        user.setUserId(1);
        user.setUserName("admin");
        user.setLastIp("192.168.1.2");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}
