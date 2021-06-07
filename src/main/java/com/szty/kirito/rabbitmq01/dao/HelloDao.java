package com.szty.kirito.rabbitmq01.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author sunyaming
 * @Classname HelloDao
 * @Description TODO
 * @Date 2021/6/7 17:01
 * @Created by sunyaming
 */
@Component
@Slf4j
public class HelloDao {

    public String sayHello(String name) {
        String returnString = "孙"+name;
        log.info("name is {},返回值为 {}", name,returnString);
        return returnString;
    }
}
