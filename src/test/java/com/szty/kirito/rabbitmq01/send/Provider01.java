package com.szty.kirito.rabbitmq01.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
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

    /**
     *  点对点模式（不需要 exchange = 交换机）
     * @throws IOException
     * @throws TimeoutException
     */
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        //获取链接
        Connection connection = MyRabbitMQConnect.getConnection();
        //创建通道
        Channel channel  = connection.createChannel();
        //参数1：  队列名称 没有这个队列会自动创建
        // 参数2  是否持久化，true持久化，false 不持久化，rabbitmq服务重启，此队列将不存在
        // 参数3  是否独占队列 true 独占(只能被当前链接使用)，false 不独占
        // 参数4  是否消费后，删除队列，true自动删除
        // 参数5  其他参数
        channel.queueDeclare("hello",true,false,false,null);
        // 参数1：交换机名称
        // 参数2: 队列名称
        // 参数3 额外参数设置。eg:消息持久化
        // 参数4  消息的具体内容
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello 第一种模式".getBytes());

        MyRabbitMQConnect.closeConnect(channel,connection);

    }
    
    
    
    @Test
    public void workerQueue01() throws IOException {
        //获取链接
        Connection connection = MyRabbitMQConnect.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //参数1：  队列名称 没有这个队列会自动创建
        // 参数2  是否持久化，true持久化，false 不持久化，rabbitmq服务重启，此队列将不存在
        // 参数3  是否独占队列 true 独占(只能被当前链接使用)，false 不独占
        // 参数4  是否消费后，删除队列，true自动删除
        // 参数5  其他参数
        channel.queueDeclare("hello",true,false,false,null);
        for (int i = 0; i <10 ; i++) {
            String message = "發送消息序號0" + i;
            channel.basicPublish("","hello",true,null,message.getBytes());
        }

//        MyRabbitMQConnect.closeConnect(channel,connection);
    }
}
