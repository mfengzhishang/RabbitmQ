package com.szty.kirito.rabbitmq01.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Classname RabbitMQConnect
 * @Description TODO
 * @Date 2021/6/7 17:58
 * @Created by sunyaming
 */
public class MyRabbitMQConnect {
    private static Connection connection ;
    private static ConnectionFactory connectionFactory ;

    public static Connection getConnection() throws IOException, TimeoutException {
        if(null == connection){
//            System.out.println("===============初始化 connection=================");
            if(null == connectionFactory){
//                System.out.println("===============初始化 connectionFactory=================");
                connectionFactory = new ConnectionFactory();
                connectionFactory.setHost("192.168.124.128");
                connectionFactory.setPort(5672);
                connectionFactory.setVirtualHost("/ems");
                connectionFactory.setUsername("guest");
                connectionFactory.setPassword("guest");
                connection = connectionFactory.newConnection();
            }else{
                connection = connectionFactory.newConnection();
            }
            return connection;
        }else{
            return connection;
        }
    }

    public static void closeConnect(Channel channel, Connection connection) throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
