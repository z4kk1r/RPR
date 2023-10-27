package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import  java.util.Random;

public class Banka {
    protected long brojRacuna;
    private List<Korisnik> korisnici;
    private List<Uposlenik> uposlenici;

    Banka() {
        korisnici= new ArrayList<>();
        uposlenici= new ArrayList<>();
    }

    Korisnik kreirajNovogKorisnika(String ime, String prezime) {
        Korisnik k = new Korisnik(ime, prezime);
        korisnici.add(k);
        return k;
    }

    Uposlenik kreirajNovogUposlenika(String ime, String prezime){
        Uposlenik u= new Uposlenik(ime, prezime);
        uposlenici.add(u);
        return u;
    }

    Racun kreirajRacunZaKorisnika(Korisnik k){
        brojRacuna++;
        Random random= new Random();
        Racun r= new Racun(random.nextLong(),k);
        k.dodajRacun(r);
        return r;
    }
}
