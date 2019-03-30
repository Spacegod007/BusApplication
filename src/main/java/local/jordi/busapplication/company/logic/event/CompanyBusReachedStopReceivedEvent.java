package local.jordi.busapplication.company.logic.event;

import local.jordi.busapplication.shared.event.EventListener;
import local.jordi.busapplication.shared.messaging.model.stopreached.CompanyBusReachedStop;

public interface CompanyBusReachedStopReceivedEvent extends EventListener<CompanyBusReachedStop> {
}
