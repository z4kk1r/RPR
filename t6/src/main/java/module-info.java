module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testfx;
    requires org.testfx.junit5;
    requires org.junit.jupiter.api;


    opens ba.unsa.etf.rpr.t6 to javafx.fxml;
    exports ba.unsa.etf.rpr.t6;
}