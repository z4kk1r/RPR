package ba.unsa.etf.rpr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LaptopDaoJSONFile implements LaptopDao{
    private ArrayList<Laptop> laptopi;
    private File file;

    public LaptopDaoJSONFile() {
        laptopi = new ArrayList<Laptop>();
        file = new File("src/main/resources/laptopi.json");

    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) throws IOException {
        ObjectMapper obj= new ObjectMapper();
        laptopi.add(laptop);
        obj.writeValue(file,laptopi);
    }

    @Override
    public Laptop getLaptop(String procesor) {
        for (Laptop x : laptopi) {
            if (x.getProcesor().equals(procesor)) {
                return x;
            }
        }
        throw new NeodgovarajuciProcesorException("Nije pronadjen takav laptop");
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {
        this.laptopi.addAll(laptopi);
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() throws IOException, ClassNotFoundException {
        ArrayList<Laptop> rez= new ArrayList<Laptop>();
        ObjectMapper obj= new ObjectMapper();
        rez=obj.readValue(file,new TypeReference<ArrayList<Laptop>>() {});
        return rez;
    }
}
