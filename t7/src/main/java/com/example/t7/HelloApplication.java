package com.example.t7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        KorisniciModel model= new KorisniciModel();
        model.napuni();
        KorisniciController ctrl= new KorisniciController(model);
        FXMLLoader loader= new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        loader.setController(ctrl);
        Scene scene = new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Hello!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}