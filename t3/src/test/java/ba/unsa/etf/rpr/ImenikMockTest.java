package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ImenikMockTest {
    private static Imenik imenik=new Imenik();

    @BeforeAll
    public static void dodaj(){
        imenik.dodaj("Meho",new FiksniBroj(Grad.SARAJEVO,"123-456"));
        imenik.dodaj("Suljo",new FiksniBroj(Grad.ZENICA,"123-457"));
        imenik.dodaj("Bego",new MobilniBroj(61,"123-458"));
        imenik.dodaj("Ado",new MedunarodniBroj("+381","123-459"));
    }

    @Test
    void testMockBroj() {
        Imenik i= Mockito.mock(Imenik.class);
        Mockito.when(i.dajBroj("Meho")).thenReturn("Nema takvog korisnika");

        String broj=i.dajBroj("Meho");
        assertEquals(broj,"Nema takvog korisnika");
    }

    @Test
    void testMock() {
        Map<String,String> mapa= Mockito.mock(Map.class);
        Mockito.when(mapa.get("Suljo")).thenReturn("Test prosao");

        String broj=mapa.get("Suljo");
        assertEquals("Test prosao",broj);
    }
}