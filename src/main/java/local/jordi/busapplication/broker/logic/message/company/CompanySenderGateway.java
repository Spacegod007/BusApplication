package local.jordi.busapplication.broker.logic.message.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import local.jordi.busapplication.shared.messaging.Sender;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyRequestSchedule;
import local.jordi.busapplication.shared.messaging.serializer.object.CompanyRequestScheduleSerializer;

public class CompanySenderGateway {
    private final String requestScheduleCompanyQueue;
    private final String busReachedStopQueue;
    private Sender sender;

    private CompanyRequestScheduleSerializer companyRequestScheduleSerializer;

    public CompanySenderGateway(String requestScheduleCompanyQueue, String busReachedStopQueue) {
        this.requestScheduleCompanyQueue = requestScheduleCompanyQueue;
        this.busReachedStopQueue = busReachedStopQueue;
        sender = new Sender();
        companyRequestScheduleSerializer = new CompanyRequestScheduleSerializer();
        sender.createQueue(requestScheduleCompanyQueue);
        sender.createQueue(busReachedStopQueue);
    }

    public void requestSchedule(CompanyRequestSchedule companyRequestSchedule)
    {
        try {
            String serialized = companyRequestScheduleSerializer.serialize(companyRequestSchedule);
            sender.sendMessage(requestScheduleCompanyQueue, serialized);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
