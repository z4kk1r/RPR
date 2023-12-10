package com.example.t7;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class KorisniciController {
    @FXML
    private TextField ime;

    @FXML
    private TextField prezime;

    @FXML
    private TextField email;

    @FXML
    private TextField uname;

    @FXML
    private TextField passwd;

    @FXML
    private Button dodajButton;

    @FXML
    private Button exitButton;

    @FXML
    private ListView<Korisnik> listaKorisnika;

    private KorisniciModel model;

    public KorisniciController(KorisniciModel model){
        this.model=model;
    }

    @FXML
    public void initialize(){
        listaKorisnika.setItems(model.getKorisnici());
        model.trenutniKorisnikProperty().addListener(
                (obj, oldKorisnik,newKorisnik)->{
                    if(oldKorisnik!=null) {
                        ime.textProperty().unbindBidirectional(oldKorisnik.imeProperty());
                        prezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty());
                        email.textProperty().unbindBidirectional(oldKorisnik.emailProperty());
                        uname.textProperty().unbindBidirectional(oldKorisnik.usernameProperty());
                        passwd.textProperty().unbindBidirectional(oldKorisnik.passwordProperty());
                    }
                    if(newKorisnik!=null){
                        ime.textProperty().bindBidirectional(newKorisnik.imeProperty());
                        prezime.textProperty().bindBidirectional(newKorisnik.prezimeProperty());
                        email.textProperty().bindBidirectional(newKorisnik.emailProperty());
                        uname.textProperty().bindBidirectional(newKorisnik.usernameProperty());
                        passwd.textProperty().bindBidirectional(newKorisnik.passwordProperty());
                    }
                }
        );
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }

    public void dodaj(ActionEvent event) {
        Korisnik k= new Korisnik("Novi","Korisnik","","","");
        model.dodajKorisnika(k);
        model.setTrenutniKorisnik(k);
    }

    public void izberiKorisnika(MouseEvent mouseEvent) {
        model.setTrenutniKorisnik(listaKorisnika.getSelectionModel().getSelectedItem());
    }
}