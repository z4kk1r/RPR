package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainTest {
    private static ArrayList<Laptop>laptopovi=new ArrayList<Laptop>();
    @BeforeAll
    public static void unosPodataka() throws IOException {
        Laptop dell=new Laptop("dell","xps",1200,12,128,256,"Intel","Nvidia",15);
        Laptop hp=new Laptop("hp","envy",1500,16,512,256,"Intel","Integrated",17);
        Laptop apple=new Laptop("apple","macbook",3200,16,512,512,"M2","Integrated",16);
        laptopovi.add(dell);
        laptopovi.add(hp);
        laptopovi.add(apple);

    }

    @Test
    void daLiVracaIsto() throws IOException {
        LaptopDaoSerializableFile fajltxt= new LaptopDaoSerializableFile();
        Laptop laptop=new Laptop("dell","xps",1200,12,128,256,"Intel","Nvidia",15);
        fajltxt.dodajLaptopUFile(laptop);
        Laptop laptxt=fajltxt.getLaptop("Intel");

        LaptopDaoJSONFile fajljson= new LaptopDaoJSONFile();
        Laptop laptopjson=new Laptop("hp","envy",1500,16,512,256,"Intel","Integrated",17);
        fajljson.dodajLaptopUFile(laptopjson);
        Laptop lapjson=fajljson.getLaptop("Intel");

        assertEquals(lapjson.getProcesor(),laptxt.getProcesor());
    }

    @Test
    void baciIzuzetak(){
        assertThrows(NeodgovarajuciProcesorException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                LaptopDaoSerializableFile fajltxt= new LaptopDaoSerializableFile();
                Laptop laptxt=fajltxt.getLaptop("Ryzen");
            }
        });

    }

    @Test
    void ispisListe() throws IOException, ClassNotFoundException {
        LaptopDaoSerializableFile fajltxt= new LaptopDaoSerializableFile();
        Laptop laptop=new Laptop("apple","macbook",3200,16,512,512,"M2","Integrated",16);
        fajltxt.dodajLaptopUFile(laptop);
        laptop=new Laptop("dell","xps",1200,12,128,256,"Intel","Nvidia",15);
        fajltxt.dodajLaptopUFile(laptop);

        ArrayList<Laptop> laptopovi=fajltxt.vratiPodatkeIzDatoteke();
        String exp="apple\ndell\n2";
        String act=fajltxt.getLaptop("M2").getBrend()+"\n"+fajltxt.getLaptop("Intel").getBrend()+"\n"+laptopovi.size();
        System.out.println(exp);
        System.out.println(act);
        assertEquals(exp,act);
    }

    @Test
    void mockingTest() throws IOException {
        LaptopDaoSerializableFile mockFile = mock(LaptopDaoSerializableFile.class);
        Laptop laptop = new Laptop("dell", "xps", 1200, 12, 128, 256, "Intel", "Nvidia", 15);
        doNothing().when(mockFile).dodajLaptopUFile(laptop);
        mockFile.dodajLaptopUFile(laptop);
        verify(mockFile).dodajLaptopUFile(laptop);
    }
}