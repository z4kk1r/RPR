package ba.unsa.etf.rpr;
import  java.util.Random;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    Banka banka=new Banka();
        Korisnik meho =banka.kreirajNovogKorisnika("meho", "mehic");
        banka.kreirajRacunZaKorisnika(meho);
        Random broj = new Random();
        Racun rmeho= new Racun(broj.nextLong(),meho);
        System.out.println(meho.toString());
    }
}
