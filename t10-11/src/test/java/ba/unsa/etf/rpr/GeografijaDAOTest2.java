package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class GeografijaDAOTest2 {

    @BeforeEach
    public void reset() throws SQLException {
        GeografijaDAO.vratiNaDefault();
    }

    @Test
    void test1() {
        int grad=GeografijaDAO.dajIdGrada("Sarajevo");
        GeografijaDAO.dodajDrzavu(new Drzava(0,"Francuska",grad));
        assertEquals("Sarajevo",GeografijaDAO.glavniGrad("Francuska").toString());
    }

    @Test
    void test2() {
        assertEquals(4,GeografijaDAO.drzave().size());
    }

    @Test
    void test3() {
        GeografijaDAO.dodajDrzavu(new Drzava(0,"Tanzanija",-1));
        GeografijaDAO.dodajGrad(new Grad(0,"Test",123456,GeografijaDAO.dajIdDrzave("Tanzanija")));
        assertEquals(5,GeografijaDAO.drzave().size());
        assertEquals("Tanzanija",GeografijaDAO.dajDrzavuPrekoGrada("Test").toString());
    }

    @Test
    void test4() {
        GeografijaDAO.obrisiGrad(new Grad(GeografijaDAO.dajIdGrada("Pariz"),"",0,0));
        GeografijaDAO.resetujGrad();
        assertEquals(6,GeografijaDAO.gradovi().size());
    }
}