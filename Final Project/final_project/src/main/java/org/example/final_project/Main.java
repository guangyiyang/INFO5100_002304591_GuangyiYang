package org.example.final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
        @Override
        public void start(Stage stage) throws Exception{
            // load main.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/final_project/main.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            // configure stage
            stage.setTitle("Image Processor App");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }

}
