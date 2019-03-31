package local.jordi.busapplication.broker.logic.message.busstop;

import local.jordi.busapplication.broker.logic.message.BrokerRegisterGateway;
import local.jordi.busapplication.broker.logic.message.IGatewayLog;
import local.jordi.busapplication.shared.messaging.Receiver;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.BusStopRegistration;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusStopBusReachedStop;

import java.util.HashMap;
import java.util.Map;

public class BusStopGateway {

    private IGatewayLog gatewayLog;

    private Map<String, BusStopSenderGateway> busStopGatewayByBusStopName;
    private BusStopReceiverGateway busStopReceiverGateway;

    public BusStopGateway(BrokerRegisterGateway registerGateway, IGatewayLog gatewayLog) {
        this.gatewayLog = gatewayLog;

        busStopGatewayByBusStopName = new HashMap<>();
        busStopReceiverGateway = new BusStopReceiverGateway();

        registerGateway.subscribeBusStopRegistrationReceived(this::busStopRegistrationReceived);
    }

    public void sendBusStopBusReachedStop(String busstop, BusStopBusReachedStop busStopBusReachedStop)
    {
        if (busStopGatewayByBusStopName.containsKey(busstop))
        {
            BusStopSenderGateway busStopSenderGateway = busStopGatewayByBusStopName.get(busstop);

            gatewayLog.log("Sending BusStopBusReachedStop, conetens: " + busStopBusReachedStop);

            busStopSenderGateway.sendBusReachedStop(busStopBusReachedStop);
        }
    }

    private void busStopRegistrationReceived(BusStopRegistration busStopRegistration) {
        BusStopSenderGateway busStopSenderGateway = new BusStopSenderGateway(busStopRegistration.getBusReachedStopListeningQueue());

        busStopGatewayByBusStopName.put(busStopRegistration.getStopName(), busStopSenderGateway);

        gatewayLog.log("BusStopRegistration received, registered: " + busStopRegistration);
    }
}
