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
public class ConsumerWorker01 {
    /**
     *  不能使用@Test (无法获取数据)
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MyRabbitMQConnect.getConnection();
        Channel channel = connection.createChannel();

        //每個通道只消費1個message
        channel.basicQos(1);
        channel.queueDeclare("hello",true,false,false,null);

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
                try{ Thread.sleep(1000); }catch (Exception e){ log.error(e.getMessage());}
                //參數1： 手動確認 ；；參數2 false每次確認1個,true 确认所有的
                channel.basicAck(envelope.getDeliveryTag(),false);
                System.out.println("获取的消息："+ new String(body));
            }
        });


//         链接关闭无法监听队列，故消费者不要关闭链接
//        MyRabbitMQConnect.closeConnect(channel,connection);


    }

}
