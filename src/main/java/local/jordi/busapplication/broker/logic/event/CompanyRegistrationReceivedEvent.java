package local.jordi.busapplication.broker.logic.event;

import local.jordi.busapplication.shared.event.EventListener;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.CompanyRegistration;

public interface CompanyRegistrationReceivedEvent extends EventListener<CompanyRegistration> {
}
