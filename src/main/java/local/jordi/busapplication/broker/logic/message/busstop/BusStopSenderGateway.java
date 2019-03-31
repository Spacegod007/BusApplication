package local.jordi.busapplication.broker.logic.message.busstop;

import com.fasterxml.jackson.core.JsonProcessingException;
import local.jordi.busapplication.shared.messaging.Sender;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusStopBusReachedStop;
import local.jordi.busapplication.shared.messaging.serializer.object.BusStopBusReachedStopSerializer;

public class BusStopSenderGateway {

    private String busReachedStopListeningQueue;

    private Sender sender;
    private BusStopBusReachedStopSerializer busStopBusReachedStopSerializer;

    public BusStopSenderGateway(String busReachedStopListeningQueue) {
        this.busReachedStopListeningQueue = busReachedStopListeningQueue;
        sender = new Sender();

        busStopBusReachedStopSerializer = new BusStopBusReachedStopSerializer();

        sender.createQueue(busReachedStopListeningQueue);
    }

    public void sendBusReachedStop(BusStopBusReachedStop busStopBusReachedStop)
    {
        try {
            String serialized = busStopBusReachedStopSerializer.serialize(busStopBusReachedStop);
            sender.sendMessage(busReachedStopListeningQueue, serialized);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
