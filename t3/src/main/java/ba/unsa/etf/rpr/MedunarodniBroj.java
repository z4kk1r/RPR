package ba.unsa.etf.rpr;

import java.util.Objects;

public class MedunarodniBroj extends TelefonskiBroj{
    private String drzava;
    private String broj;

    public MedunarodniBroj(String drzava, String broj){
        if(drzava==null || broj==null) throw new BrojException("Broj ne moze biti null");
        this.drzava=drzava;
        this.broj=broj;
    }


    @Override
    public String ispisi() {
        return  new StringBuffer(drzava).append("/").append(broj).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(drzava,broj);
    }
}
