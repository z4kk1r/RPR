package ba.unsa.etf.rpr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int izlaz=1;
        LaptopDaoSerializableFile fajltxt= new LaptopDaoSerializableFile();
        LaptopDaoJSONFile fajljson= new LaptopDaoJSONFile();
        LaptopDaoXMLFile fajlxml= new LaptopDaoXMLFile();
        Scanner ulaz= new Scanner(System.in);

        /*while(izlaz!=0) {
            System.out.println("Unesite naziv za laptop (IZLAZ za izlaz): ");
            String naziv=ulaz.nextLine();
            if(naziv.equals("IZLAZ")){
                break;
            }
            System.out.println("Model: ");
            String model= ulaz.nextLine();


            System.out.println("Cijena: ");
            int cijena= ulaz.nextInt();

            System.out.println("RAM: ");
            int ram= ulaz.nextInt();

            System.out.println("HDD: ");
            int hdd= ulaz.nextInt();

            System.out.println("SSD: ");
            int ssd= ulaz.nextInt();

            ulaz.nextLine();

            System.out.println("CPU: ");
            String cpu= ulaz.nextLine();

            System.out.println("GPU: ");
            String gpu= ulaz.nextLine();
            System.out.println("SIZE: ");
            int size= ulaz.nextInt();

            System.out.println("1: Tekstualna\n2:JSON\n3:XML");
            int izbor=ulaz.nextInt();
            switch (izbor){
                case 1:{
                    Laptop laptop=new Laptop(naziv,model,cijena,ram,hdd,ssd,cpu,gpu,size);
                    fajltxt.dodajLaptopUFile(laptop);
                    System.out.println("Do sada u fajlu se nalaze sljedeci brendovi (modeli):");
                    ArrayList<Laptop> laptopi= new ArrayList<Laptop>();
                    laptopi=fajltxt.vratiPodatkeIzDatoteke();
                    for(Laptop x: laptopi){
                        System.out.println(x.getBrend()+" ("+x.getModel()+")");
                    }
                    break;
                } case 2:{
                    Laptop laptop=new Laptop(naziv,model,cijena,ram,hdd,ssd,cpu,gpu,size);
                    fajljson.dodajLaptopUFile(laptop);
                    System.out.println("Do sada u fajlu se nalaze sljedeci brendovi (modeli):");
                    ArrayList<Laptop> laptopi= new ArrayList<Laptop>();
                    laptopi=fajljson.vratiPodatkeIzDatoteke();
                    for(Laptop x: laptopi){
                        System.out.println(x.getBrend()+" ("+x.getModel()+")");
                    }
                    break;
                }case 3:{
                    Laptop laptop=new Laptop(naziv,model,cijena,ram,hdd,ssd,cpu,gpu,size);
                    fajlxml.dodajLaptopUFile(laptop);
                    System.out.println("Do sada u fajlu se nalaze sljedeci brendovi (modeli):");
                    ArrayList<Laptop> laptopi= new ArrayList<Laptop>();
                    laptopi=fajlxml.vratiPodatkeIzDatoteke();
                    for(Laptop x: laptopi){
                        System.out.println(x.getBrend()+" ("+x.getModel()+")");
                    }
                    break;
                } default:{
                    System.out.println("Pogresan izbor!");
                }
            }
            ulaz.nextLine();

        }*/
        Laptop hp=new Laptop("HP","Envy",1400,16,512,512,"Intel","Nvidia 4070Ti",15);
        Laptop dell=new Laptop("Dell","XPS",1200,16,256,512,"Intel i9 12th gen","Nvidia 4060Ti",17);
        LaptopDaoSerializableFile fajl= new LaptopDaoSerializableFile();
        fajltxt.dodajLaptopUFile(dell);
        fajltxt.dodajLaptopUFile(hp);
        Laptop laptxt=fajltxt.getLaptop("Intel");
        System.out.println(laptxt.getBrend());

    }
}