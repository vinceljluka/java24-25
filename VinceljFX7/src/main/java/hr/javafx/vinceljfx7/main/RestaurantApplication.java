package hr.javafx.vinceljfx7.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RestaurantApplication extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:./src/main/resources/hr/javafx/vinceljfx7/firstScreen.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("firstScreen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getMainStageStage() {
        return mainStage;
    }

}