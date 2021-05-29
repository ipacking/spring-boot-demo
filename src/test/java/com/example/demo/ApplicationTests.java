package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class ApplicationTests {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserService userService;

    @Test
    void TestRequest() {
        ResponseEntity<String> res = restTemplate.getForEntity("http://www.baidu.com", null, String.class);
        log.info("res:{}", res);
    }

    @Test
    void TestUser() {
        int count = userService.userCount();
        Assert.isTrue(count > 0, "user is null");
        List<User> users = userService.userList();
        log.info("user list:{}", users);
        Gson gson = new Gson();
        String str = gson.toJson(users);
        log.info(str);
    }

    @Test
    void TestGson() {
        User user = new User();
        user.setUsername("jack");
        user.setPassword("123456");
        //对象转字符串
        log.info(new Gson().toJson(user));
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user);
        list.add(user);
        log.info(new Gson().toJson(list));

        //字符串转对象
        User u = new Gson().fromJson("{\"name\":\"jack\",\"pass\":\"123456\"}", User.class);
        log.info(u.toString());
        List<User> us = new Gson().fromJson("[{\"name\":\"jack\",\"pass\":\"123456\"},{\"name\":\"jack\",\"pass\":\"123456\"},{\"name\":\"jack\",\"pass\":\"123456\"}]",
                new TypeToken<List<User>>() {
                }.getType());
        log.info(us.toString());
    }

}
