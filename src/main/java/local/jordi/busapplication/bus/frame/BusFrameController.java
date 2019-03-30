package local.jordi.busapplication.bus.frame;

import javafx.application.Platform;
import local.jordi.busapplication.bus.logic.BusLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import local.jordi.busapplication.shared.model.BusSchedule;

public class BusFrameController {
    private BusLogic busLogic;

    @FXML
    public TextField txtfldCompany;

    @FXML
    public TextField txtfldNumber;

    @FXML
    public Button btnStopReached;

    @FXML
    public Button btnSetBus;

    @FXML
    public ListView lstvStops;

    public BusFrameController()
    {
        busLogic = new BusLogic();
        busLogic.subscribeList(this::listReceived);
    }

    private void listReceived(BusSchedule busSchedule) {
        Platform.runLater(() -> lstvStops.setItems(busLogic.getSchedule()));
    }

    public void handleStopReached(ActionEvent actionEvent) {
        busLogic.stopReached();
    }

    public void handleSetBus(ActionEvent actionEvent) {
        int busNumber = Integer.parseInt(txtfldNumber.getText());
        String company = txtfldCompany.getText();
        busLogic.setBus(busNumber, company);
    }
}
