package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    Controller controller = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller(primaryStage);
        constructUI();
        primaryStage.setTitle("AR-PUF Simulation");
        primaryStage.show();
    }

    private void constructUI() {
        controller.createUI();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
