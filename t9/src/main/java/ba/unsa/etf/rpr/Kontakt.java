package ba.unsa.etf.rpr;

public class Kontakt {
    private String ime,kontakt;
    public Kontakt(){
        ime="";
        kontakt="";
    }

    public Kontakt(String ime, String kontakt){
        this.ime=ime;
        this.kontakt=kontakt;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }
}
