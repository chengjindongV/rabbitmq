package com.rabbit.rabbitmq.fastStart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: chengjindong
 * @Date: 2019/9/10 21:51
 */
public class Procuder {

    public static void main(String[] args) throws Exception{
        // 1.创建一个ConnectionFactory,并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2.通过连接工厂创建一个连接
        Connection connection = connectionFactory.newConnection();

        // 3.通过connection创建一个Channel
        Channel channel = connection.createChannel();

        // 4.通过channerl发送数据
        for (int i = 0; i < 5; i++){
            String msg = "hello rabbitmq";
            channel.basicPublish("","test001",null,msg.getBytes());
        }

        // 5.记得要关闭相关的连接
        channel.close();
        connection.close();
    }
}
