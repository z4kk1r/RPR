package ba.unsa.etf.rpr;

public class Racun {
    private long brojRacuna;
    private Osoba korisnikRacina;
    private boolean odobrenjePrekoracenja;
    private double stanjeRacuna;

    Racun(long broj, Osoba covjek){
        this.brojRacuna=broj;
        this.korisnikRacina=covjek;
    }

    boolean provjeriOdobrenjePrekoracenja(){
        return this.odobrenjePrekoracenja;
    }

    boolean izvrsiUplatu(double cifra){
        if(cifra>0) {
            this.stanjeRacuna += cifra;
            return true;
        }
        return false;
    }

    boolean izvrsiIsplatu(double cifra){
        if(cifra>0 && this.stanjeRacuna>0) {
            this.stanjeRacuna -= cifra;
            return true;
        }
        return false;
    }

    void odobriPrekoracenje(){
        this.odobrenjePrekoracenja=true;
    }

}
