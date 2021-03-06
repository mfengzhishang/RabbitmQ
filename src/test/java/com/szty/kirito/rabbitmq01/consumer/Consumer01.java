package com.szty.kirito.rabbitmq01.consumer;

import com.rabbitmq.client.*;
import com.szty.kirito.rabbitmq01.utils.MyRabbitMQConnect;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Classname Consumer01
 * @Description TODO
 * @Date 2021/6/8 9:37
 * @Created by sunyaming
 */
@Slf4j
public class Consumer01 {
    /**
     *  不能使用@Test (无法获取数据)
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MyRabbitMQConnect.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello01", true, false, false, null);
        //参数1：队列名称，
        // 参数2：ack 自动确认机制true 自动确认，
        // 参数3：consumer实现，获取消息
         channel.basicConsume("hello",false, new DefaultConsumer(channel){

            //参数1：标签，
            // 参数2：信封
            // 参数3：额外属性
            // 4 消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取的消息："+ new String(body));
            }
        });

//         链接关闭无法监听队列，故消费者不要关闭链接
//        MyRabbitMQConnect.closeConnect(channel,connection);

    }

}
