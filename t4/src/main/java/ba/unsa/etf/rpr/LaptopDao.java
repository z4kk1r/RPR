package ba.unsa.etf.rpr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface LaptopDao{
    public void dodajLaptopUListu(Laptop laptop);
    public void dodajLaptopUFile(Laptop laptop) throws IOException;
    public Laptop getLaptop(String procesor);
    public void napuniListu(ArrayList<Laptop> laptopi);
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() throws IOException, ClassNotFoundException;
}

