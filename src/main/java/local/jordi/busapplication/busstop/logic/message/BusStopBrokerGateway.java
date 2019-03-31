package local.jordi.busapplication.busstop.logic.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import local.jordi.busapplication.busstop.logic.event.BusStopBusReachedStopReceivedEvent;
import local.jordi.busapplication.shared.StaticStrings;
import local.jordi.busapplication.shared.event.EventContainer;
import local.jordi.busapplication.shared.messaging.Receiver;
import local.jordi.busapplication.shared.messaging.Sender;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.BusStopRegistration;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusStopBusReachedStop;
import local.jordi.busapplication.shared.messaging.serializer.object.BusStopBusReachedStopSerializer;
import local.jordi.busapplication.shared.messaging.serializer.object.BusStopRegistrationSerializer;

import java.io.IOException;

public class BusStopBrokerGateway {

    private EventContainer<BusStopBusReachedStopReceivedEvent, BusStopBusReachedStop> busStopBusReachedStopListeners;

    private Sender sender;
    private Receiver busReachedStopReceiver;

    private BusStopBusReachedStopSerializer busStopBusReachedStopSerializer;

    public BusStopBrokerGateway() {
        busStopBusReachedStopListeners = new EventContainer<>();

        sender = new Sender();
        busStopBusReachedStopSerializer = new BusStopBusReachedStopSerializer();
        sender.createQueue(StaticStrings.BROKER_REGISTER_BUSSTOP_QUEUE);
    }

    public void subscribeBusStopReached(BusStopBusReachedStopReceivedEvent listener)
    {
        busStopBusReachedStopListeners.Subscribe(listener);
    }

    public void RegisterBusStop(String busStopName)
    {
        String busStopBusReachedStopListeningQueue = generateBusStopBusReachedStopListeningQueue(busStopName);
        BusStopRegistration busStopRegistration = new BusStopRegistration(busStopName, busStopBusReachedStopListeningQueue);
        BusStopRegistrationSerializer busStopRegistrationSerializer = new BusStopRegistrationSerializer();

        try {
            String serialized = busStopRegistrationSerializer.serialize(busStopRegistration);
            sender.sendMessage(StaticStrings.BROKER_REGISTER_BUSSTOP_QUEUE, serialized);

            initListener(busStopBusReachedStopListeningQueue);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void initListener(String busStopBusReachedStopListeningQueue) {
        busReachedStopReceiver = new Receiver(busStopBusReachedStopListeningQueue, this::busReachedStopReceived);
    }

    private void busReachedStopReceived(String serialized) {
        try {
            BusStopBusReachedStop busStopBusReachedStop = busStopBusReachedStopSerializer.deserialize(serialized);
            busStopBusReachedStopListeners.Fire(busStopBusReachedStop);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateBusStopBusReachedStopListeningQueue(String busStopName)
    {
        return StaticStrings.BUSSTOP_REACHED_BUSSTOP_QUEUE + "." + busStopName;
    }
}
