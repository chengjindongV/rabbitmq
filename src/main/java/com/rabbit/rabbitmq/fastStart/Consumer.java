package com.rabbit.rabbitmq.fastStart;

import com.rabbitmq.client.*;

/**
 * @Author: chengjindong
 * @Date: 2019/9/10 21:50
 */
public class Consumer { //5.6.0

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

        // 4.声明一个队列
        String queueName = "test001";
        channel.queueDeclare(queueName,true,false,false,null);

        // 5.创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 6.设置channel
        channel.basicConsume(queueName,true,queueingConsumer);

        // 7.获取消息

        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println("消费者: " + message);

//            Envelope envelope = delivery.getEnvelope();
//            long deliveryTag = envelope.getDeliveryTag();
        }


    }
}
