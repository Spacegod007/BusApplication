package local.jordi.busapplication.bus.frame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import local.jordi.busapplication.shared.StaticStrings;

public class BusProgram extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("busFrame.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Bus");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
