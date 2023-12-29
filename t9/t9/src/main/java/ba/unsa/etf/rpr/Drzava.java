package ba.unsa.etf.rpr;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Drzava {
    private int id,glavni_grad;
    private String naziv;
    public Drzava(){
        id=0;
        glavni_grad=0;
        naziv=new String();
    }

    public Drzava(ResultSet result){
        try {
            id=result.getInt(1);
            naziv=result.getString(2);
            glavni_grad=result.getInt(3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public Drzava(int id, String naziv, int glavni_grad) {
        this.id = id;
        this.glavni_grad = glavni_grad;
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGlavni_grad() {
        return glavni_grad;
    }

    public void setGlavni_grad(int glavni_grad) {
        this.glavni_grad = glavni_grad;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
