module ba.unsa.etf.rpr.t1011 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ba.unsa.etf.rpr to javafx.fxml;
    exports ba.unsa.etf.rpr;
}