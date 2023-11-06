package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ImenikTest {
    private static Imenik imenik=new Imenik();
    @BeforeAll
    public static void dodaj(){
        imenik.dodaj("Meho",new FiksniBroj(Grad.SARAJEVO,"123-456"));
        imenik.dodaj("Suljo",new FiksniBroj(Grad.ZENICA,"123-457"));
        imenik.dodaj("Bego",new MobilniBroj(61,"123-458"));
        imenik.dodaj("Ado",new MedunarodniBroj("+381","123-459"));
    }
    @Test
    void dajBroj() {
        String broj=imenik.dajBroj("Meho");
        assertEquals("033/123-456",broj);
    }

    @Test
    void dajNullBroj() {
        String broj=imenik.dajBroj("Mehaga");
        assertNull(broj);
    }

    @Test
    void dajGradNull(){
        assertThrows(BrojException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                imenik.dodaj("Hamo",new FiksniBroj(null,"111-222"));
            }
        });
    }
}