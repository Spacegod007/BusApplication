package local.jordi.busapplication.broker.logic.message.company;

import local.jordi.busapplication.broker.logic.event.CompanyReplyScheduleReceivedEvent;
import local.jordi.busapplication.shared.StaticStrings;
import local.jordi.busapplication.shared.event.EventContainer;
import local.jordi.busapplication.shared.messaging.Receiver;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyReplySchedule;
import local.jordi.busapplication.shared.messaging.serializer.object.CompanyReplyScheduleSerializer;

import java.io.IOException;

public class CompanyReceiverGateway {
    private EventContainer<CompanyReplyScheduleReceivedEvent, CompanyReplySchedule> companyReplyScheduleListeners;

    private Receiver receiver;

    private CompanyReplyScheduleSerializer companyReplyScheduleSerializer;

    public CompanyReceiverGateway() {
        companyReplyScheduleListeners = new EventContainer<>();
        companyReplyScheduleSerializer = new CompanyReplyScheduleSerializer();

        receiver = new Receiver(StaticStrings.REPLY_SCHEDULE_BROKER_QUEUE, this::companyReplyScheduleReceived);
    }

    public void subscribeCompanyReplySchedule(CompanyReplyScheduleReceivedEvent listener)
    {
        companyReplyScheduleListeners.Subscribe(listener);
    }

    private void companyReplyScheduleReceived(String serialized) {
        try {
            CompanyReplySchedule companyReplySchedule = companyReplyScheduleSerializer.deserialize(serialized);
            companyReplyScheduleListeners.Fire(companyReplySchedule);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
