package local.jordi.busapplication.broker.logic.message.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import local.jordi.busapplication.shared.messaging.Sender;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyRequestSchedule;
import local.jordi.busapplication.shared.messaging.model.stopreached.CompanyBusReachedStop;
import local.jordi.busapplication.shared.messaging.serializer.object.CompanyBusReachedStopSerializer;
import local.jordi.busapplication.shared.messaging.serializer.object.CompanyRequestScheduleSerializer;

public class CompanySenderGateway {
    private String requestScheduleCompanyQueue;
    private String busReachedStopQueue;

    private Sender sender;

    private CompanyRequestScheduleSerializer companyRequestScheduleSerializer;
    private CompanyBusReachedStopSerializer companyBusReachedStopSerializer;

    public CompanySenderGateway(String requestScheduleCompanyQueue, String busReachedStopQueue) {
        this.requestScheduleCompanyQueue = requestScheduleCompanyQueue;
        this.busReachedStopQueue = busReachedStopQueue;

        sender = new Sender();

        companyRequestScheduleSerializer = new CompanyRequestScheduleSerializer();
        companyBusReachedStopSerializer = new CompanyBusReachedStopSerializer();

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

    public void sendCompanyBusReachedStop(CompanyBusReachedStop companyBusReachedStop) {
        try
        {
            String serialized = companyBusReachedStopSerializer.serialize(companyBusReachedStop);
            sender.sendMessage(busReachedStopQueue, serialized);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
