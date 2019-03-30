package local.jordi.busapplication.broker.logic.message.bus;

import com.fasterxml.jackson.core.JsonProcessingException;
import local.jordi.busapplication.shared.messaging.Sender;
import local.jordi.busapplication.shared.messaging.model.busschedule.BusReplySchedule;
import local.jordi.busapplication.shared.messaging.serializer.object.BusReplyScheduleSerializer;

public class BusSenderGateway {

    private String replyScheduleQueue;

    private BusReplyScheduleSerializer busReplyScheduleSerializer;
    private Sender sender;

    public BusSenderGateway(String replyScheduleQueue) {
        this.replyScheduleQueue = replyScheduleQueue;

        busReplyScheduleSerializer = new BusReplyScheduleSerializer();
        sender = new Sender();

        sender.createQueue(replyScheduleQueue);
    }

    public void sendReplySchedule(BusReplySchedule busReplySchedule)
    {
        try {
            String serialized = busReplyScheduleSerializer.serialize(busReplySchedule);
            sender.sendMessage(replyScheduleQueue, serialized);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
