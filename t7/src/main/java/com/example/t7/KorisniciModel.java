package com.example.t7;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    private ObservableList<Korisnik> korisnici= FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutniKorisnik=new SimpleObjectProperty<>();

    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void dodajKorisnika(Korisnik k){
        korisnici.add(k);
    }

    public void napuni(){
        korisnici.add(new Korisnik("Meho","Mehic","mmehic1@gmail.com","mmehic1","123456"));
        korisnici.add(new Korisnik("Suljo","Suljic","ssuljic2@gmail.com","ssuljic2","543219"));
        korisnici.add(new Korisnik("Bego","Begic","bbegic3@gmail.com","bbegic3","451278"));
        trenutniKorisnik.set(null);
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }
}
