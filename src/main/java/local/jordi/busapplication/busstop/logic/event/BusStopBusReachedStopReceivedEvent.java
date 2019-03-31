package local.jordi.busapplication.busstop.logic.event;

import local.jordi.busapplication.shared.event.EventListener;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusStopBusReachedStop;

public interface BusStopBusReachedStopReceivedEvent extends EventListener<BusStopBusReachedStop> {
}
