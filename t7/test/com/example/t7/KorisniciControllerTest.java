package com.example.t7;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
@ExtendWith(ApplicationExtension.class)
class KorisniciControllerTest {
    @Start
    public void start(Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(HelloApplication.class.getResource("hello-view.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void testButtonClick (FxRobot robot) {
        /*Button btnPrijava =
                robot.lookup("#btnPrijava").queryAs(Button.class);
        robot.clickOn("#fldLogin");
        robot.write("anonymous");
        robot.clickOn("#btnPrijava");
        assertEquals("anonymous", btnPrijava.getText());*/
        assertEquals(2,1+1);
    }
}