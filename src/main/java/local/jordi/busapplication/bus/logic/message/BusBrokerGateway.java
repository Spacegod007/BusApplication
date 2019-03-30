package local.jordi.busapplication.bus.logic.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import local.jordi.busapplication.shared.StaticStrings;
import local.jordi.busapplication.bus.logic.event.ScheduleReceivedEvent;
import local.jordi.busapplication.shared.event.EventContainer;
import local.jordi.busapplication.shared.messaging.Receiver;
import local.jordi.busapplication.shared.messaging.Sender;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.BusRegistration;
import local.jordi.busapplication.shared.messaging.model.busschedule.BusReplySchedule;
import local.jordi.busapplication.shared.messaging.model.busschedule.BusRequestSchedule;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusReachedStop;
import local.jordi.busapplication.shared.messaging.serializer.object.BusReachedStopSerializer;
import local.jordi.busapplication.shared.messaging.serializer.object.BusRegistrationSerializer;
import local.jordi.busapplication.shared.messaging.serializer.object.BusReplyScheduleSerializer;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.BusReachedStopDeserializer;
import local.jordi.busapplication.shared.model.BusSchedule;
import local.jordi.busapplication.shared.messaging.serializer.object.BusRequestScheduleSerializer;
import local.jordi.busapplication.shared.model.BusynessLevel;

import java.io.IOException;

public class BusBrokerGateway {

    private EventContainer<ScheduleReceivedEvent, BusSchedule> scheduleReceivedListeners;

    private Sender sender;
    private Receiver receiver;

    public BusBrokerGateway() {
        scheduleReceivedListeners = new EventContainer<>();

        sender = new Sender();

        sender.createQueue(StaticStrings.REQUEST_SCHEDULE_BROKER_QUEUE);
        sender.createQueue(StaticStrings.BUSSTOP_REACHED_BROKER_QUEUE);
        sender.createQueue(StaticStrings.BROKER_REGISTER_BUS_QUEUE);
    }

    public void registerBus(int busNumber, String company)
    {
        String scheduleListeningQueue = generateListeningQueue(busNumber, company);
        BusRegistration busRegistration = new BusRegistration(busNumber, company, scheduleListeningQueue);

        try
        {
            BusRegistrationSerializer busRegistrationSerializer = new BusRegistrationSerializer();
            String serialized = busRegistrationSerializer.serialize(busRegistration);
            sender.sendMessage(StaticStrings.BROKER_REGISTER_BUS_QUEUE, serialized);

            initReceiver(scheduleListeningQueue);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void initReceiver(String scheduleListeningQueue) {
        receiver = new Receiver(scheduleListeningQueue, this::scheduleReceived);
    }

    public void requestSchedule(int busNumber, String company)
    {
        BusRequestSchedule busRequestSchedule = new BusRequestSchedule(busNumber, company);
        BusRequestScheduleSerializer busRequestScheduleSerializer = new BusRequestScheduleSerializer();

        try
        {
            String serialized = busRequestScheduleSerializer.serialize(busRequestSchedule);
            sender.sendMessage(StaticStrings.REQUEST_SCHEDULE_BROKER_QUEUE, serialized);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void scheduleReceived(String serialized) {
        BusReplyScheduleSerializer busReplyScheduleSerializer = new BusReplyScheduleSerializer();

        try
        {
            BusReplySchedule busReplySchedule = busReplyScheduleSerializer.deserialize(serialized);
            scheduleReceivedListeners.Fire(busReplySchedule.getBusSchedule());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void busStopReached(int busNumber, String company, String reachedStop, String nextStop, BusynessLevel busynessLevel) {
        BusReachedStopSerializer busReachedStopSerializer = new BusReachedStopSerializer();
        BusReachedStop busReachedStop = new BusReachedStop(busNumber, company, reachedStop, nextStop, busynessLevel);
        try {
            String serialized = busReachedStopSerializer.serialize(busReachedStop);
            sender.sendMessage(StaticStrings.BUSSTOP_REACHED_BROKER_QUEUE, serialized);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(ScheduleReceivedEvent listener)
    {
        scheduleReceivedListeners.Subscribe(listener);
    }

    public void unsubscribe(ScheduleReceivedEvent listener)
    {
        scheduleReceivedListeners.UnSubscribe(listener);
    }

    private String generateListeningQueue(int number, String company)
    {
        return String.format("%s.nr%d_%s", StaticStrings.REPLY_SCHEDULE_BUS_QUEUE, number, company);
    }
}
