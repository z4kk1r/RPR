package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class GradoviDrzava {
    SimpleIntegerProperty idGrada, brojStanovnika;
    SimpleStringProperty nazivGrada,nazivDrzave;

    public GradoviDrzava(int idGrada, String naziv, int broj_stanovnika, String nazivDrzave) {
        this.idGrada = new SimpleIntegerProperty(idGrada);
        this.brojStanovnika = new SimpleIntegerProperty(broj_stanovnika);
        this.nazivDrzave = new SimpleStringProperty(nazivDrzave);
        this.nazivGrada = new SimpleStringProperty(naziv);
    }

    public GradoviDrzava( ) {
        this.idGrada = new SimpleIntegerProperty(0);
        this.brojStanovnika = new SimpleIntegerProperty(0);
        this.nazivDrzave = new SimpleStringProperty("");
        this.nazivGrada = new SimpleStringProperty("");
    }

    public GradoviDrzava(Object selectedItem) {
        GradoviDrzava red=(GradoviDrzava) selectedItem;
        this.idGrada = new SimpleIntegerProperty(red.idGrada.get());
        this.brojStanovnika = new SimpleIntegerProperty(red.brojStanovnika.get());
        this.nazivDrzave = new SimpleStringProperty(red.nazivDrzave.get());
        this.nazivGrada = new SimpleStringProperty(red.nazivDrzave.get());
    }

    public int getIdGrada() {
        return idGrada.get();
    }

    public SimpleIntegerProperty idGradaProperty() {
        return idGrada;
    }

    public void setIdGrada(int idGrada) {
        this.idGrada.set(idGrada);
    }

    public int getBrojStanovnika() {
        return brojStanovnika.get();
    }

    public SimpleIntegerProperty brojStanovnikaProperty() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika.set(brojStanovnika);
    }

    public String getNazivGrada() {
        return nazivGrada.get();
    }

    public SimpleStringProperty nazivGradaProperty() {
        return nazivGrada;
    }

    public void setNazivGrada(String nazivGrada) {
        this.nazivGrada.set(nazivGrada);
    }

    public String getNazivDrzave() {
        return nazivDrzave.get();
    }

    public SimpleStringProperty nazivDrzaveProperty() {
        return nazivDrzave;
    }

    public void setNazivDrzave(String nazivDrzave) {
        this.nazivDrzave.set(nazivDrzave);
    }

    @Override
    public String toString() {
        return idGrada.get()+" "+nazivGrada.get()+" "+brojStanovnika.get()+" "+nazivDrzave.get();
    }
}
