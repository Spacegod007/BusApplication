package local.jordi.busapplication.broker.logic.event;

import local.jordi.busapplication.shared.event.EventListener;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.BusRegistration;

public interface BusRegistrationReceivedEvent extends EventListener<BusRegistration> {
}
