package local.jordi.busapplication.company.logic.event;

import local.jordi.busapplication.shared.event.EventListener;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyRequestSchedule;

public interface CompanyRequestScheduleReceivedEvent extends EventListener<CompanyRequestSchedule> {
}
