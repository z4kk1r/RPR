package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GeografijaDAOTest {

    @BeforeAll
    public static void start() throws SQLException {
        GeografijaDAO.vratiNaDefault();
    };

    @Test
    void test1() {
        int grad=GeografijaDAO.dajIdGrada("London");
        GeografijaDAO.dodajDrzavu(new Drzava(0,"Francuska",grad));
        assertEquals("London",GeografijaDAO.glavniGrad("Francuska").toString());
    }

    @Test
    void test2() {
        assertEquals(5,GeografijaDAO.drzave().size());
    }

    @Test
    void test3() {
        GeografijaDAO.dodajDrzavuId(GeografijaDAO.dajIdGrada("Pariz"),GeografijaDAO.dajIdDrzave("Francuska"));
        assertEquals("Francuska",GeografijaDAO.dajDrzavuPrekoGrada("Pariz"));
    }

    @Test
    void test4() {
        GeografijaDAO.obrisiGrad(new Grad(GeografijaDAO.dajIdGrada("London"),"",0,0));
        assertEquals(5,GeografijaDAO.gradovi().size());
    }
}