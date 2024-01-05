package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GradController {
    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;
    public ChoiceBox choiceDrzava;
    public Button btnOK;
    public Button btnCancel;

    private boolean sadrziSlovo(String text){
        for(int i=0;i<text.length();i++){
            if(text.toLowerCase().charAt(i)>='a' && text.toLowerCase().charAt(i)<='z') return true;
        }
        return false;
    }

    @FXML
    public void initialize(){
        choiceDrzava.setItems(GeografijaDAO.sveDrzave());
        fieldNaziv.getStyleClass().add("neispravno");
        fieldBrojStanovnika.getStyleClass().add("neispravno");
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

        fieldBrojStanovnika.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(fieldBrojStanovnika.getText().trim().isEmpty() || fieldBrojStanovnika.getText().trim().startsWith("-") || sadrziSlovo(fieldBrojStanovnika.getText())){
                    fieldBrojStanovnika.getStyleClass().add("neispravno");
                }else{
                    fieldBrojStanovnika.getStyleClass().removeAll("neispravno");
                }
            }
        });
    }

    public void confirm(ActionEvent event) {
        if(fieldNaziv.getStyleClass().contains("neispravno") || fieldBrojStanovnika.getStyleClass().contains("nesipravno")) return;
        ArrayList<Grad>gradovi=GeografijaDAO.gradovi();
        for(Grad g:gradovi){
            if(g.getNaziv().equals(fieldNaziv.getText())){
                GeografijaDAO.izmijeniGrad(new Grad(GeografijaDAO.dajIdGrada(fieldNaziv.getText()),fieldNaziv.getText(),Integer.parseInt(fieldBrojStanovnika.getText()),GeografijaDAO.dajIdDrzave(choiceDrzava.getValue().toString())));
                return;
            }
        }
        if(choiceDrzava.getValue()==null){
            GeografijaDAO.dodajGrad(new Grad(0,fieldNaziv.getText(),Integer.parseInt(fieldBrojStanovnika.getText()),-1));
        }else
        GeografijaDAO.dodajGrad(new Grad(0,fieldNaziv.getText(),Integer.parseInt(fieldBrojStanovnika.getText()),GeografijaDAO.dajIdDrzave(choiceDrzava.getValue().toString())));
    }

    public void cancel(ActionEvent event) {
        Node src= (Node) event.getSource();
        Stage stage= (Stage) src.getScene().getWindow();
        stage.close();
    }
}
