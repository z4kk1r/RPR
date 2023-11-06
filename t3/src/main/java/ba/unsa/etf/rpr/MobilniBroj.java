package ba.unsa.etf.rpr;

import java.util.Objects;

public class MobilniBroj extends TelefonskiBroj{
    private int mobilnaMreza;
    private String broj;
    public MobilniBroj(int mobilnaMreza, String broj){
        if(mobilnaMreza==0 || broj==null) throw new BrojException("Broj ne moze biti null");
        this.mobilnaMreza=mobilnaMreza;
        this.broj=broj;
    }
    @Override
    public String ispisi() {
        return new StringBuffer("0").append(mobilnaMreza).append("/").append(broj).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobilnaMreza,broj);
    }
}
