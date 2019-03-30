package local.jordi.busapplication.company.logic.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import local.jordi.busapplication.company.logic.event.CompanyRequestScheduleReceivedEvent;
import local.jordi.busapplication.shared.StaticStrings;
import local.jordi.busapplication.shared.event.EventContainer;
import local.jordi.busapplication.shared.messaging.Receiver;
import local.jordi.busapplication.shared.messaging.Sender;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.CompanyRegistration;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyReplySchedule;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyRequestSchedule;
import local.jordi.busapplication.shared.messaging.serializer.object.CompanyRegistrationSerializer;
import local.jordi.busapplication.shared.messaging.serializer.object.CompanyReplyScheduleSerializer;
import local.jordi.busapplication.shared.messaging.serializer.object.CompanyRequestScheduleSerializer;
import local.jordi.busapplication.shared.model.BusSchedule;

import java.io.IOException;

public class CompanyBrokerGateway {

    EventContainer<CompanyRequestScheduleReceivedEvent, CompanyRequestSchedule> companyRequestScheduleListeners;

    private Sender sender;
    private Receiver busStopReceiver;
    private Receiver scheduleRequestReceiver;

    private String companyScheduleRequestListeningQueue;
    private String companyBusReachedStopListeningQueue;

    public CompanyBrokerGateway() {
        companyRequestScheduleListeners = new EventContainer<>();
        sender = new Sender();
        sender.createQueue(StaticStrings.BROKER_REGISTER_COMPANY_QUEUE);
    }

    public void registerCompany(String companyName)
    {
        companyScheduleRequestListeningQueue = generateCompanyScheduleRequestListeningQueue(companyName);
        companyBusReachedStopListeningQueue = generateCompanyBusReachedStopListeningQueue(companyName);

        initReceivers(companyScheduleRequestListeningQueue, companyBusReachedStopListeningQueue);
        
        CompanyRegistration companyRegistration = new CompanyRegistration(companyName, companyScheduleRequestListeningQueue, companyBusReachedStopListeningQueue);
        CompanyRegistrationSerializer companyRegistrationSerializer = new CompanyRegistrationSerializer();

        try
        {
            String serialized = companyRegistrationSerializer.serialize(companyRegistration);
            sender.sendMessage(StaticStrings.BROKER_REGISTER_COMPANY_QUEUE, serialized);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void SubscribeCompanyRequestScheduleReceived(CompanyRequestScheduleReceivedEvent listener)
    {
        companyRequestScheduleListeners.Subscribe(listener);
    }

    public void sendReplySchedule(BusSchedule busSchedule) {

        CompanyReplySchedule companyReplySchedule = new CompanyReplySchedule(busSchedule.getCompany(), busSchedule.getBusNumber(), busSchedule);

        CompanyReplyScheduleSerializer companyReplyScheduleSerializer = new CompanyReplyScheduleSerializer();

        try {
            String serialized = companyReplyScheduleSerializer.serialize(companyReplySchedule);
            sender.sendMessage(StaticStrings.REPLY_SCHEDULE_BROKER_QUEUE, serialized);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void initReceivers(String companyScheduleRequestListeningQueue, String companyBusReachedStopListeningQueue) {
        busStopReceiver = new Receiver(companyBusReachedStopListeningQueue, this::busReachedStopReceived);
        scheduleRequestReceiver = new Receiver(companyScheduleRequestListeningQueue, this::scheduleRequestReceived);
    }

    private void scheduleRequestReceived(String serialized) {
        CompanyRequestScheduleSerializer companyRequestScheduleSerializer = new CompanyRequestScheduleSerializer();

        try {
            CompanyRequestSchedule companyRequestSchedule = companyRequestScheduleSerializer.deserialize(serialized);
            companyRequestScheduleListeners.Fire(companyRequestSchedule);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void busReachedStopReceived(String serialized) {

    }

    private String generateCompanyBusReachedStopListeningQueue(String companyName) {
        return StaticStrings.BUSSTOP_REACHED_COMPANY_QUEUE + "." + companyName;
    }

    private String generateCompanyScheduleRequestListeningQueue(String companyName) {
        return StaticStrings.REQUEST_SCHEDULE_COMPANY_QUEUE + "." + companyName;
    }
}
