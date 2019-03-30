package local.jordi.busapplication.broker.logic.message;

import local.jordi.busapplication.shared.StaticStrings;
import local.jordi.busapplication.broker.logic.event.BusRegistrationReceivedEvent;
import local.jordi.busapplication.broker.logic.event.BusStopRegistrationReceivedEvent;
import local.jordi.busapplication.broker.logic.event.CompanyRegistrationReceivedEvent;
import local.jordi.busapplication.shared.event.EventContainer;
import local.jordi.busapplication.shared.messaging.Receiver;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.BusRegistration;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.BusStopRegistration;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.CompanyRegistration;
import local.jordi.busapplication.shared.messaging.serializer.object.BusRegistrationSerializer;
import local.jordi.busapplication.shared.messaging.serializer.object.BusStopRegistrationSerializer;
import local.jordi.busapplication.shared.messaging.serializer.object.CompanyRegistrationSerializer;

import java.io.IOException;

public class BrokerRegisterGateway
{
    private EventContainer<CompanyRegistrationReceivedEvent, CompanyRegistration> companyRegistrationListeners;
    private EventContainer<BusRegistrationReceivedEvent, BusRegistration> busRegistrationListeners;
    private EventContainer<BusStopRegistrationReceivedEvent, BusStopRegistration> busStopRegistrationListeners;

    private CompanyRegistrationSerializer companyRegistrationSerializer;
    private BusRegistrationSerializer busRegistrationSerializer;
    private BusStopRegistrationSerializer busStopRegistrationSerializer;

    private Receiver companyRegistrationReceiver;
    private Receiver busRegistrationReceiver;
    private Receiver busStopRegistrationReceiver;

    public BrokerRegisterGateway() {
        companyRegistrationListeners = new EventContainer<>();
        busRegistrationListeners = new EventContainer<>();
        busStopRegistrationListeners = new EventContainer<>();

        companyRegistrationSerializer = new CompanyRegistrationSerializer();
        busRegistrationSerializer = new BusRegistrationSerializer();
        busStopRegistrationSerializer = new BusStopRegistrationSerializer();

        companyRegistrationReceiver = new Receiver(StaticStrings.BROKER_REGISTER_COMPANY_QUEUE, this::companyRegistrationReceived);
        busRegistrationReceiver = new Receiver(StaticStrings.BROKER_REGISTER_BUS_QUEUE, this::busRegistrationReceived);
        busStopRegistrationReceiver = new Receiver(StaticStrings.BROKER_REGISTER_BUSSTOP_QUEUE, this::busStopRegistrationReceived);
    }

    public void subscribeCompanyRegistrationReceived(CompanyRegistrationReceivedEvent listener)
    {
        companyRegistrationListeners.Subscribe(listener);
    }

    public void subscribeBusRegistrationReceived(BusRegistrationReceivedEvent listener)
    {
        busRegistrationListeners.Subscribe(listener);
    }

    public void subscribeBusStopRegistrationReceived(BusStopRegistrationReceivedEvent listener)
    {
        busStopRegistrationListeners.Subscribe(listener);
    }

    private void companyRegistrationReceived(String serialized) {
        try {
            CompanyRegistration companyRegistration = companyRegistrationSerializer.deserialize(serialized);
            companyRegistrationListeners.Fire(companyRegistration);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void busRegistrationReceived(String serialized) {
        try
        {
            BusRegistration busRegistration = busRegistrationSerializer.deserialize(serialized);
            busRegistrationListeners.Fire(busRegistration);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void busStopRegistrationReceived(String serialized) {
        try
        {
            BusStopRegistration busStopRegistration = busStopRegistrationSerializer.deserialize(serialized);
            busStopRegistrationListeners.Fire(busStopRegistration);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
