package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrzavaController {
    public Button btnOK;
    public Button btnCancel;
    public ChoiceBox choiceGrad;
    public TextField fieldNaziv;


    @FXML
    public void initialize(){
        choiceGrad.setItems(GeografijaDAO.sviGradovi());
        fieldNaziv.getStyleClass().add("neispravno");
        fieldNaziv.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(fieldNaziv.getText().trim().isEmpty()){
                    fieldNaziv.getStyleClass().add("neispravno");
                }else{
                    fieldNaziv.getStyleClass().removeAll("neispravno");
                }
            }
        });
    }

    public void confirm(ActionEvent event) {
        if(fieldNaziv.getStyleClass().contains("neispravno")) return;
        if(choiceGrad.getValue()==null) {
            System.out.println("NAL");
            GeografijaDAO.dodajDrzavu(new Drzava(0, fieldNaziv.getText(), -1));
        }
            else
        GeografijaDAO.dodajDrzavu(new Drzava(0,fieldNaziv.getText(),GeografijaDAO.dajIdGrada(choiceGrad.getValue().toString())));
            GeografijaDAO.dodajDrzavuId(GeografijaDAO.dajIdGrada(choiceGrad.getValue().toString()),GeografijaDAO.dajIdDrzave(fieldNaziv.getText()));
    }

    public void cancel(ActionEvent event) {
        Node source = (Node) event.getSource();

        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
