module com.example.t7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.t7 to javafx.fxml;
    exports com.example.t7;
}