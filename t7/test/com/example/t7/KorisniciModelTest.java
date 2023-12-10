package com.example.t7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KorisniciModelTest {
    private static KorisniciModel model= new KorisniciModel();

    @BeforeAll
    public static void start(){
        model.napuni();
    }

    @Test
    void dodajKorisnika() {
        Korisnik k= new Korisnik("Novi", "Korisnik","","","");
        model.dodajKorisnika(k);
        assertEquals(4,model.getKorisnici().size());
    }

    @Test
    void getTrenutniKorisnik() {
        Korisnik k= new Korisnik("Novi", "Korisnik","","","");
        assertEquals(null,model.getTrenutniKorisnik());
        model.setTrenutniKorisnik(k);
        assertEquals("Novi",model.getTrenutniKorisnik().getIme());
    }

    @Test
    void test3() {
        Korisnik k= new Korisnik("Novi", "Korisnik","","","");
        model.setTrenutniKorisnik(k);
        assertEquals("Novi",model.getTrenutniKorisnik().getIme());
        model.getTrenutniKorisnik().setIme("Amar");
        model.getTrenutniKorisnik().setPrezime("Hodzic");
        assertEquals("Amar Hodzic",model.getTrenutniKorisnik().getIme()+" "+model.getTrenutniKorisnik().getPrezime());
    }
}