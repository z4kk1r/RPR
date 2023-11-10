package ba.unsa.etf.rpr;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LaptopDaoSerializableFile implements LaptopDao {
    private ArrayList<Laptop> laptopi;
    private File file;

    public LaptopDaoSerializableFile() {
        laptopi = new ArrayList<Laptop>();
        file = new File("src/main/resources/laptopi.txt");

    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) throws IOException {
        FileOutputStream input = new FileOutputStream(file);
        ObjectOutputStream lap = new ObjectOutputStream(input);
        laptopi.add(laptop);
        lap.writeObject(laptopi);
        lap.close();
        input.close();
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
        FileInputStream input = new FileInputStream(file);
        ObjectInputStream is = new ObjectInputStream(input);
        return (ArrayList<Laptop>) is.readObject();
    }
}
