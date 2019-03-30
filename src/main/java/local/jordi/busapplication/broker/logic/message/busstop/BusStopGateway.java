package local.jordi.busapplication.broker.logic.message.busstop;

import local.jordi.busapplication.broker.logic.message.BrokerRegisterGateway;
import local.jordi.busapplication.broker.logic.message.IGatewayLog;
import local.jordi.busapplication.shared.messaging.Receiver;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.BusStopRegistration;

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

    private void busStopRegistrationReceived(BusStopRegistration busStopRegistration) {
        BusStopSenderGateway busStopSenderGateway = new BusStopSenderGateway();

        busStopGatewayByBusStopName.put(busStopRegistration.getStopName(), busStopSenderGateway);

        gatewayLog.log("BusStopRegistration received, registered: " + busStopRegistration);
    }

    private void registerEvents(BusStopSenderGateway busStopSenderGateway) {

    }
}
