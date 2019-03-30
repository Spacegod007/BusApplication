package local.jordi.busapplication.broker.logic.event;

import local.jordi.busapplication.shared.event.EventListener;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusReachedStop;

public interface BusReachedStopReceivedEvent extends EventListener<BusReachedStop> {
}
