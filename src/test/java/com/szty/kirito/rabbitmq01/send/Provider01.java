package com.szty.kirito.rabbitmq01.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.szty.kirito.rabbitmq01.utils.MyRabbitMQConnect;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Classname Send
 * @Description TODO
 * @Date 2021/6/7 17:22
 * @Created by sunyaming
 */
public class Provider01 {

    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        //获取链接
        Connection connection = MyRabbitMQConnect.getConnection();
        //创建通道
        Channel channel  = connection.createChannel();
        //参数1：队列名称，
        // 参数2:是否持久化，true持久化，false 不持久化
        // 参数3 是否独占队列 true 独占，false 不独占
        // 参数4  是否消费后，删除队列，true自动删除
        // 参数5  其他参数
        channel.queueDeclare("hello01",true,false,false,null);
        // 参数1：交换机名称
        // 参数2: 队列名称
        // 参数3 额外参数设置
        // 参数4  消息的具体内容
        channel.basicPublish("","hello01",null,"hello 第一种模式".getBytes());

    /*    channel.close();
        connection.close();*/

    }
}
