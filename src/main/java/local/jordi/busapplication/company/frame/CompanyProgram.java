package local.jordi.busapplication.company.frame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CompanyProgram extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("companyFrame.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Broker");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
