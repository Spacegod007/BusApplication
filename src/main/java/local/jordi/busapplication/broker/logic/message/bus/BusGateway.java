package local.jordi.busapplication.broker.logic.message.bus;

import local.jordi.busapplication.broker.logic.event.BusReachedStopReceivedEvent;
import local.jordi.busapplication.broker.logic.event.BusRequestScheduleReceivedEvent;
import local.jordi.busapplication.broker.logic.message.BrokerRegisterGateway;
import local.jordi.busapplication.broker.logic.message.IGatewayLog;
import local.jordi.busapplication.broker.logic.model.BusReference;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.BusRegistration;
import local.jordi.busapplication.shared.messaging.model.busschedule.BusReplySchedule;

import java.util.HashMap;
import java.util.Map;

public class BusGateway {

    private final IGatewayLog gatewayLog;
    private Map<BusReference, BusSenderGateway> busGatewayByBusReference;
    private BusReceiverGateway busReceiverGateway;

    public BusGateway(BrokerRegisterGateway registerGateway, IGatewayLog gatewayLog) {
        this.gatewayLog = gatewayLog;
        busGatewayByBusReference = new HashMap<>();
        busReceiverGateway = new BusReceiverGateway();
        registerGateway.subscribeBusRegistrationReceived(this::busRegistrationReceived);
    }

    private void busRegistrationReceived(BusRegistration busRegistration) {
        BusSenderGateway busSenderGateway = new BusSenderGateway(busRegistration.getBusScheduleReplyListeningQueue());

        BusReference busReference = new BusReference(busRegistration.getBusNumber(), busRegistration.getCompany());
        busGatewayByBusReference.put(busReference, busSenderGateway);

        gatewayLog.log("BusRegistration received, registered: " + busReference);
    }

    public void sendBusReplySchedule(BusReference busReference, BusReplySchedule busReplySchedule)
    {
        if (busGatewayByBusReference.containsKey(busReference))
        {
            BusSenderGateway busSenderGateway = busGatewayByBusReference.get(busReference);

            gatewayLog.log("Sending BusReplySchedule, contents: " + busReplySchedule);

            busSenderGateway.sendReplySchedule(busReplySchedule);
        }
    }

    public void subscribeBusReachedStopReceived(BusReachedStopReceivedEvent listener)
    {
        busReceiverGateway.subscribeBusReachedStop(listener);
    }

    public void subscribeBusRequestScheduleReceived(BusRequestScheduleReceivedEvent listener)
    {
        busReceiverGateway.subscribeBusScheduleRequestReceived(listener);
    }
}
