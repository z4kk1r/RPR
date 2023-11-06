package ba.unsa.etf.rpr;

import java.util.Objects;

public class FiksniBroj extends TelefonskiBroj{
    private Grad grad;
    private String broj;
    public FiksniBroj(Grad grad, String broj){
        if(grad==null) throw new BrojException("Grad ne moze biti null");
        this.grad=grad;
        this.broj=broj;
    }
    @Override
    public String ispisi() {

        return new StringBuffer(grad.toString()).append("/").append(broj).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(grad,broj);
    }
}
