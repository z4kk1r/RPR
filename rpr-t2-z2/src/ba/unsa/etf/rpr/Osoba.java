package ba.unsa.etf.rpr;

public class Osoba {
    protected String ime, prezime;
    Osoba(String ime, String prezime){
        this.ime=ime;
        this.prezime=prezime;
    }

    @Override
    public String toString() {
        return new StringBuffer("Osoba: ").append(this.ime).append(" ").append(this.prezime).toString();
    }

}
