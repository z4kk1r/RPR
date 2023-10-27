package ba.unsa.etf.rpr;

public class Korisnik extends Osoba{
    private Racun racun;

    Korisnik(String ime, String prezime){
        super(ime,prezime);
    }

    void dodajRacun(Racun r){
        this.racun=r;
    }
}
