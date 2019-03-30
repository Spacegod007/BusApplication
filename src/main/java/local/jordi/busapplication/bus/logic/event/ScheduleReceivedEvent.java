package local.jordi.busapplication.bus.logic.event;

import local.jordi.busapplication.shared.event.EventListener;
import local.jordi.busapplication.shared.model.BusSchedule;

public interface ScheduleReceivedEvent extends EventListener<BusSchedule> {
}
