package local.jordi.busapplication.busstop.logic;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import local.jordi.busapplication.busstop.logic.message.BusStopBrokerGateway;
import local.jordi.busapplication.busstop.logic.model.BusStop;
import local.jordi.busapplication.busstop.logic.model.BusStopBusReference;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusStopBusReachedStop;

public class BusStopLogic {

    private BusStop busStop;
    private BusStopBrokerGateway busStopBrokerGateway;

    private ObservableList<BusStopBusReference> buses;

    public BusStopLogic() {
        busStopBrokerGateway = new BusStopBrokerGateway();
    }

    public void setBusStop(String busStopName) {
        busStop = new BusStop(busStopName);

        buses = FXCollections.observableArrayList();

        busStopBrokerGateway.RegisterBusStop(busStopName);
        busStopBrokerGateway.subscribeBusStopReached(this::busStopReachedReceived);
    }

    private void busStopReachedReceived(BusStopBusReachedStop busStopBusReachedStop) {
        BusStopBusReference busStopBusReference = new BusStopBusReference(busStopBusReachedStop.getBusNumber(), busStopBusReachedStop.getCompany(), busStopBusReachedStop.getBusynessLevel());
        Platform.runLater(() -> buses.add(busStopBusReference));
    }

    public ObservableList<BusStopBusReference> getBuses() {
        return buses;
    }
}
