package local.jordi.busapplication.broker.logic.event;

import local.jordi.busapplication.shared.event.EventListener;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyReplySchedule;

public interface CompanyReplyScheduleReceivedEvent extends EventListener<CompanyReplySchedule> {
}
