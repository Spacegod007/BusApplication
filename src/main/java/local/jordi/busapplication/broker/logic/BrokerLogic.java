package local.jordi.busapplication.broker.logic;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import local.jordi.busapplication.broker.logic.message.IGateway;
import local.jordi.busapplication.broker.logic.message.MainBrokerGateway;

public class BrokerLogic {

    private ObservableList messages;
    private IGateway gateway;

    public BrokerLogic() {
        messages = FXCollections.observableArrayList();
        gateway = new MainBrokerGateway();
        gateway.subscribe(this::messageReceived);
    }

    private void messageReceived(String brokerMessage) {
        Platform.runLater(() -> messages.add(brokerMessage));
    }

    public ObservableList getMessages()
    {
        return messages;
    }
}
