package local.jordi.busapplication.broker.logic.event;

import local.jordi.busapplication.shared.event.EventListener;
import local.jordi.busapplication.shared.messaging.model.busschedule.BusRequestSchedule;

public interface BusRequestScheduleReceivedEvent extends EventListener<BusRequestSchedule> {
}
