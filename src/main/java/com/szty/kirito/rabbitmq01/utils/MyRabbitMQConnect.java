package com.szty.kirito.rabbitmq01.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @Classname RabbitMQConnect
 * @Description TODO
 * @Date 2021/6/7 17:58
 * @Created by sunyaming
 */
@Slf4j
public class MyRabbitMQConnect {

    private static ConnectionFactory connectionFactory ;
    static {
       log.info("初始化connectionFactory...");
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.124.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
    }

    public static Connection getConnection()  {
        try{
            return connectionFactory.newConnection();
        }catch (Exception e){
            log.info("创建连接失败！！");
            return null;
        }
    }

    public static void closeConnect(Channel channel, Connection connection){
        try{
            if(channel!=null){
                channel.close();
            }
            if(connection!=null){
                connection.close();
            }
        }catch (Exception e){
            log.error("连接关闭失败；error:{}",e.getMessage());
        }

    }
}
