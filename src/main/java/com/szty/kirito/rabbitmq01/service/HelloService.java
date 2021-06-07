package com.szty.kirito.rabbitmq01.service;

import com.szty.kirito.rabbitmq01.dao.HelloDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname HelloService
 * @Description TODO
 * @Date 2021/6/7 16:58
 * @Created by sunyaming
 */
@Slf4j
@Service
public class HelloService {
    @Autowired
    private HelloDao helloDao;


    public String sayHello(String name) {
      return helloDao.sayHello(name);
    }
}
