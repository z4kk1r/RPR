package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public class start{
        public static GeografijaDAO geo= null;

        public static void define() throws ClassNotFoundException {
            geo=GeografijaDAO.getInstance();
        }
    }
    public static String ispisiGradove() throws ClassNotFoundException {
        ArrayList<Grad>lista=start.geo.gradovi();
        String gradovi="";
        for(Grad x:lista){
            gradovi+=x.getNaziv()+"("+start.geo.dajDrzavuPrekoGrada(x.getNaziv())+")"+"-"+x.getBroj_stanovnika()+"\n";
        }
        return gradovi;
    }

    public static void glavniGrad(){
        System.out.println("Unesite naziv drzave: ");
        Scanner ulaz= new Scanner(System.in);
        String drzava=ulaz.nextLine();
        System.out.println("Glavni grad drzave "+drzava+" je "+start.geo.glavniGrad(drzava).getNaziv());
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        start.define();
        System.out.println(ispisiGradove());
        glavniGrad();
    }
}