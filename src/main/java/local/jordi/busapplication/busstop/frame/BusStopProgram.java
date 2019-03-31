package local.jordi.busapplication.busstop.frame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BusStopProgram extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("busStopFrame.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Bus stop");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
