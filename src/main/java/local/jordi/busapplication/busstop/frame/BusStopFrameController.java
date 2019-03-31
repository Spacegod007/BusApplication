package local.jordi.busapplication.busstop.frame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import local.jordi.busapplication.busstop.logic.BusStopLogic;

public class BusStopFrameController {

    private BusStopLogic busStopLogic;

    @FXML
    public Button btnSetStop;

    @FXML
    public TextField txtfldBusStopName;

    @FXML
    public ListView lstvBuses;


    public BusStopFrameController() {
        busStopLogic = new BusStopLogic();
    }


    public void setBusStop(ActionEvent actionEvent) {
        busStopLogic.setBusStop(txtfldBusStopName.getText());
        lstvBuses.setItems(busStopLogic.getBuses());
    }
}
