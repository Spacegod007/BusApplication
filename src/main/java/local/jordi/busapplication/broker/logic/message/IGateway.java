package local.jordi.busapplication.broker.logic.message;

import local.jordi.busapplication.broker.logic.event.BrokerMessageReceived;

public interface IGateway {
    void subscribe(BrokerMessageReceived listener);

}
