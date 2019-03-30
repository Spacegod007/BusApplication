package local.jordi.busapplication.broker.frame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BrokerProgram extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("brokerFrame.fxml"));
        Parent root = fxmlLoader.load();

        BrokerFrameController brokerFrameController = fxmlLoader.getController();
        brokerFrameController.Init();

        primaryStage.setTitle("Broker");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
