package local.jordi.busapplication.broker.frame;


import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import local.jordi.busapplication.broker.logic.BrokerLogic;


public class BrokerFrameController {

    private BrokerLogic brokerLogic;

    @FXML
    public ListView lstvMessages;

    public BrokerFrameController() {

    }

    public void Init()
    {
        this.brokerLogic = new BrokerLogic();
        lstvMessages.setItems(brokerLogic.getMessages());
    }
}
