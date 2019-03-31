package local.jordi.busapplication.bus.logic;

import javafx.collections.ObservableList;
import local.jordi.busapplication.bus.logic.event.BusReplyScheduleReceivedEvent;
import local.jordi.busapplication.bus.logic.message.BusBrokerGateway;
import local.jordi.busapplication.bus.logic.model.Bus;
import local.jordi.busapplication.shared.event.EventContainer;
import local.jordi.busapplication.shared.model.BusSchedule;

public class BusLogic {
    private static final int standardCapacity = 40;

    private EventContainer<BusReplyScheduleReceivedEvent, BusSchedule> busReplyScheduleReceivedListeners;

    private Bus bus;
    private BusBrokerGateway busBrokerGateway;

    public BusLogic() {
        busReplyScheduleReceivedListeners = new EventContainer<>();
        busBrokerGateway = new BusBrokerGateway();
        busBrokerGateway.subscribe(this::busScheduleReceived);
    }

    private void busScheduleReceived(BusSchedule busSchedule) {
        bus.setBusSchedule(busSchedule);
        busReplyScheduleReceivedListeners.Fire(busSchedule);
    }

    public String getBusCompany() {
        return bus.getCompany();
    }

    public int getBusNumber() {
        return bus.getNumber();
    }

    public void setBus(int number, String company)
    {
        bus = new Bus(company, number, standardCapacity);
        busBrokerGateway.registerBus(number, company);
        busBrokerGateway.requestSchedule(number, company);
    }

    public ObservableList getSchedule() {
        return bus.getBusSchedule();
    }

    public void stopReached() {
        String reachedStop = bus.busStopReached();
        String nextStop = bus.getNextStop();
        if (!nextStop.equals("")) {
            busBrokerGateway.busStopReached(bus.getNumber(), bus.getCompany(), reachedStop, nextStop, bus.getBusynessLevel());
        } else {
            busBrokerGateway.requestSchedule(bus.getNumber(), bus.getCompany());
        }
    }

    public void subscribeList(BusReplyScheduleReceivedEvent listener) {
        busReplyScheduleReceivedListeners.Subscribe(listener);
    }
}
