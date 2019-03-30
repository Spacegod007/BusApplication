package local.jordi.busapplication.shared.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {

    private Channel channel;

    public Sender() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }

    public void createQueue(String queueName)
    {
        try {
            channel.queueDeclare(queueName, false, false, false, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String queueName, String message)
    {
        try {
            System.out.println("Send: " + message);
            channel.basicPublish("", queueName, null, message.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
