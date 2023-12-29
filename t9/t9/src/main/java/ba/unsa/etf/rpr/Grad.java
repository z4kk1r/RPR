package ba.unsa.etf.rpr;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Grad {
    private int id,broj_stanovnika,drzava;
    private String naziv;
    public Grad(){
        id=0;
        broj_stanovnika=0;
        drzava=0;
        naziv=new String();
    }

    public Grad(ResultSet result){
        try {
            id=result.getInt(1);
            naziv=result.getString(2);
            broj_stanovnika=result.getInt(3);
            drzava=result.getInt(4);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public Grad(int id, String naziv, int broj_stanovnika, int drzava) {
        this.id = id;
        this.broj_stanovnika = broj_stanovnika;
        this.drzava = drzava;
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBroj_stanovnika() {
        return broj_stanovnika;
    }

    public void setBroj_stanovnika(int broj_stanovnika) {
        this.broj_stanovnika = broj_stanovnika;
    }

    public int getDrzava() {
        return drzava;
    }

    public void setDrzava(int drzava) {
        this.drzava = drzava;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
