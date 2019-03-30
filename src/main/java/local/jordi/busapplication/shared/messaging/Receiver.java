package local.jordi.busapplication.shared.messaging;

import com.rabbitmq.client.*;
import local.jordi.busapplication.shared.event.EventContainer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Receiver {

    private EventContainer<MessageReceivedEvent, String> eventContainer;
    private Channel channel;

    public Receiver(String queueName, MessageReceivedEvent listener) {
        this(queueName);
        subscribe(listener);
    }

    public Receiver(String queueName) {
        eventContainer = new EventContainer<>();
        buildReceiver(queueName);
    }

    private void buildReceiver(String queueName)
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try
        {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(queueName, false, false, false, null);

            DeliverCallback deliverCallback = this::handleDeliverCallback;

            channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {});
        }
        catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    private void handleDeliverCallback(String consumerTag, Delivery delivery) throws IOException
    {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        try
        {
            System.out.println("Received: " + message);
            eventContainer.Fire(message);
        }
        finally
        {
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    public void subscribe(MessageReceivedEvent listener)
    {
        eventContainer.Subscribe(listener);
    }

    public void unsubscribe(MessageReceivedEvent listener)
    {
        eventContainer.UnSubscribe(listener);
    }
}
