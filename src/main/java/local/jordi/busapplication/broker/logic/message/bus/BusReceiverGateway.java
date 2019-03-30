package local.jordi.busapplication.broker.logic.message.bus;

import local.jordi.busapplication.broker.logic.event.BusReachedStopReceivedEvent;
import local.jordi.busapplication.broker.logic.event.BusRequestScheduleReceivedEvent;
import local.jordi.busapplication.shared.StaticStrings;
import local.jordi.busapplication.shared.event.EventContainer;
import local.jordi.busapplication.shared.messaging.Receiver;
import local.jordi.busapplication.shared.messaging.model.busschedule.BusRequestSchedule;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusReachedStop;
import local.jordi.busapplication.shared.messaging.serializer.object.BusReachedStopSerializer;
import local.jordi.busapplication.shared.messaging.serializer.object.BusRequestScheduleSerializer;

import java.io.IOException;

public class BusReceiverGateway {
    private EventContainer<BusRequestScheduleReceivedEvent, BusRequestSchedule> busRequestReceivedListeners;
    private EventContainer<BusReachedStopReceivedEvent, BusReachedStop> busReachedStopListeners;
    private BusRequestScheduleSerializer busRequestScheduleSerializer;
    private BusReachedStopSerializer busReachedStopSerializer;
    private Receiver busScheduleReceiver;
    private Receiver busStopReachedReceiver;

    public BusReceiverGateway() {
        busRequestReceivedListeners = new EventContainer<>();
        busReachedStopListeners = new EventContainer<>();

        busRequestScheduleSerializer = new BusRequestScheduleSerializer();
        busReachedStopSerializer = new BusReachedStopSerializer();

        busScheduleReceiver = new Receiver(StaticStrings.REQUEST_SCHEDULE_BROKER_QUEUE, this::BusScheduleRequestReceived);
        busStopReachedReceiver = new Receiver(StaticStrings.BUSSTOP_REACHED_BROKER_QUEUE, this::BusReachedStop);
    }

    private void BusReachedStop(String serialized) {
        try {
            BusReachedStop busReachedStop = busReachedStopSerializer.deserialize(serialized);
            busReachedStopListeners.Fire(busReachedStop);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribeBusReachedStop(BusReachedStopReceivedEvent listener)
    {
        busReachedStopListeners.Subscribe(listener);
    }

    public void subscribeBusScheduleRequestReceived(BusRequestScheduleReceivedEvent listener)
    {
        busRequestReceivedListeners.Subscribe(listener);
    }

    private void BusScheduleRequestReceived(String serializedRequest) {
        try
        {
            BusRequestSchedule busRequestSchedule = busRequestScheduleSerializer.deserialize(serializedRequest);
            busRequestReceivedListeners.Fire(busRequestSchedule);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
