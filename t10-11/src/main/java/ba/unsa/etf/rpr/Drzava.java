package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Drzava {
    private SimpleStringProperty naziv;
    private SimpleIntegerProperty id,glavni_grad;

    public Drzava(){
        id=new SimpleIntegerProperty(0);
        glavni_grad=new SimpleIntegerProperty(0);
        naziv=new SimpleStringProperty("");
    }

    public Drzava( int id,String naziv, Integer glavni_grad) {
        this.naziv = new SimpleStringProperty(naziv);
        this.id = new SimpleIntegerProperty(id);
        this.glavni_grad =new SimpleIntegerProperty(0);
        this.glavni_grad.set(glavni_grad);
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

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Integer getGlavni_grad() {
    return glavni_grad.get();
    }

    public SimpleIntegerProperty glavni_gradProperty() {
        return glavni_grad;
    }

    public void setGlavni_grad(int glavni_grad) {
        this.glavni_grad.set(glavni_grad);
    }

    @Override
    public String toString() {
        return naziv.get();
    }
}
