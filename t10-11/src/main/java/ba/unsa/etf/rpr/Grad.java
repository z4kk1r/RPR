package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Grad {
    private SimpleIntegerProperty id,broj_stanovnika,drzava;
    private SimpleStringProperty naziv;

    public Grad(int id, String naziv, int broj_stanovnika, Integer drzava) {
        this.id = new SimpleIntegerProperty(id);
        this.broj_stanovnika =new SimpleIntegerProperty(broj_stanovnika);
        this.drzava = new SimpleIntegerProperty(drzava);
        this.naziv = new SimpleStringProperty(naziv);
    }

    public Grad(){
        this.id = new SimpleIntegerProperty(0);
        this.broj_stanovnika =new SimpleIntegerProperty(0);
        this.drzava = new SimpleIntegerProperty(0);
        this.naziv = new SimpleStringProperty("");
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getBroj_stanovnika() {
        return broj_stanovnika.get();
    }

    public SimpleIntegerProperty broj_stanovnikaProperty() {
        return broj_stanovnika;
    }

    public void setBroj_stanovnika(int broj_stanovnika) {
        this.broj_stanovnika.set(broj_stanovnika);
    }

    public Integer getDrzava() {
        return drzava.get();
    }

    public SimpleIntegerProperty drzavaProperty() {
        return drzava;
    }

    public void setDrzava(int drzava) {
        this.drzava.set(drzava);
    }

    public String getNaziv() {
        return naziv.get();
    }

    public SimpleStringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    @Override
    public String toString() {
        return naziv.get();
    }
}
