package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void test1() {
        KolekcijaImena popis= new KolekcijaImena();
        popis.dodajIme("suljo suljic");
        popis.dodajIme("avdaga avdovic");

        assertEquals("avdaga avdovic",popis.getNajduzeIme());
    }

    @Test
    void test2(){
        KolekcijaImenaIPrezimena popis= new KolekcijaImenaIPrezimena();
        popis.dodajImeIPrezime("suljo","suljic");
        popis.dodajImeIPrezime("haso","hasic");
        popis.dodajImeIPrezime("benjamin","benjaminovic");
        popis.dodajImeIPrezime("salih","hodzic");
        assertEquals(2,popis.getIndexNajduzegPara());
        assertEquals("haso hasic",popis.getImeIPrezime(1));
        Pobjednik osoba= new Pobjednik(popis);
        assertEquals("benjamin",osoba.getIme());
    }

    @Test
    void test3(){
        InformacijeONastavniku nastavnik1= new InformacijeONastavniku();
        nastavnik1.setIme("pero");
        nastavnik1.setPrezime("peric");
        InformacijeOStudentu student1= new InformacijeOStudentu();
        student1.setIme("mujo");
        student1.setPrezime("mujic");

        InformacijeOStudentu student2= new InformacijeOStudentu();
        student2.setIme("haso");
        student2.setPrezime("hasic");
        student1.ocijeniNastavnika(nastavnik1,4);
        student2.ocijeniNastavnika(nastavnik1,5);

        assertEquals(4.5,nastavnik1.dajOcjenuZaNastavnika());
    }

    @Test
    void test4(){
        InformacijeONastavniku nastavnik1= new InformacijeONastavniku();
        nastavnik1.setIme("pero");
        nastavnik1.setPrezime("peric");

        InformacijeONastavniku nastavnik2= new InformacijeONastavniku();
        nastavnik1.setIme("haso");
        nastavnik1.setPrezime("hasic");

        assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                nastavnik1.ocijeniNastavnika(nastavnik2,4);
            }
        });
    }

    @Test
    void test5(){
        InformacijeONastavniku nastavnik1= new InformacijeONastavniku();
        nastavnik1.setIme("pero");
        nastavnik1.setPrezime("peric");
        InformacijeOStudentu student1= new InformacijeOStudentu();
        student1.setIme("mujo");
        student1.setPrezime("mujic");

        InformacijeOStudentu student2= new InformacijeOStudentu();
        student2.setIme("haso");
        student2.setPrezime("hasic");
        student1.ocijeniNastavnika(nastavnik1,4);
        student2.ocijeniNastavnika(nastavnik1,5);

        assertEquals(4.5,nastavnik1.dajOcjenuZaNastavnika());
    }

}