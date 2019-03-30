package local.jordi.busapplication.broker.logic.message.bus;

import local.jordi.busapplication.broker.logic.event.BusRequestScheduleReceivedEvent;
import local.jordi.busapplication.shared.StaticStrings;
import local.jordi.busapplication.shared.event.EventContainer;
import local.jordi.busapplication.shared.messaging.Receiver;
import local.jordi.busapplication.shared.messaging.model.busschedule.BusRequestSchedule;
import local.jordi.busapplication.shared.messaging.serializer.object.BusRequestScheduleSerializer;

import java.io.IOException;

public class BusReceiverGateway {
    private EventContainer<BusRequestScheduleReceivedEvent, BusRequestSchedule> listeners;
    private BusRequestScheduleSerializer busRequestScheduleSerializer;
    private Receiver receiver;

    public BusReceiverGateway() {
        listeners = new EventContainer<>();
        busRequestScheduleSerializer = new BusRequestScheduleSerializer();
        receiver = new Receiver(StaticStrings.REQUEST_SCHEDULE_BROKER_QUEUE, this::BusScheduleRequestReceived);
    }

    public void subscribeBusScheduleRequestReceived(BusRequestScheduleReceivedEvent listener)
    {
        listeners.Subscribe(listener);
    }

    private void BusScheduleRequestReceived(String serializedRequest) {
        try
        {
            BusRequestSchedule busRequestSchedule = busRequestScheduleSerializer.deserialize(serializedRequest);
            listeners.Fire(busRequestSchedule);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
