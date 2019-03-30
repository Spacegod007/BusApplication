package local.jordi.busapplication.company.frame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import local.jordi.busapplication.company.logic.CompanyLogic;

public class CompanyFrameController {
    private CompanyLogic companyLogic;

    @FXML
    public TextField txtfldCompanyName;

    @FXML
    public Button btnSetCompany;

    @FXML
    public ListView lstvBusses;

    public CompanyFrameController() {
    }

    public void setCompany(ActionEvent actionEvent) {
        companyLogic = new CompanyLogic(txtfldCompanyName.getText());
        lstvBusses.setItems(companyLogic.getActiveBusses());
    }
}
