package local.jordi.busapplication.shared.messaging.model.busschedule;

import local.jordi.busapplication.shared.model.BusSchedule;

import java.io.Serializable;

public class BusReplySchedule implements Serializable
{
    BusSchedule busSchedule;

    public BusReplySchedule(BusSchedule busSchedule) {
        setBusSchedule(busSchedule);
    }

    public BusReplySchedule() {
    }

    public BusSchedule getBusSchedule() {
        return busSchedule;
    }

    public void setBusSchedule(BusSchedule busSchedule) {
        this.busSchedule = busSchedule;
    }
}
