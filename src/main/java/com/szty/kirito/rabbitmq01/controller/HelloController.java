package com.szty.kirito.rabbitmq01.controller;

import com.szty.kirito.rabbitmq01.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description TODO
 * @Date 2021/6/7 16:57
 * @Created by sunyaming
 */
@RestController
@Slf4j
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("hello")
    public String hello(String name){
        log.info("获取参数name:{}",name);
        name = helloService.sayHello(name);
        return name;
    }

}
