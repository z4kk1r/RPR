package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static String ispisiGradove() throws ClassNotFoundException {
        ArrayList<Grad>lista=GeografijaDAO.gradovi();
        String gradovi="";
        for(Grad x:lista){
            gradovi+=x.getNaziv()+"("+GeografijaDAO.dajDrzavuPrekoGrada(x.getNaziv())+")"+"-"+x.getBroj_stanovnika()+"\n";
        }
        return gradovi;
    }

    public static void glavniGrad(){
        System.out.println("Unesite naziv drzave: ");
        Scanner ulaz= new Scanner(System.in);
        String drzava=ulaz.nextLine();
        System.out.println("Glavni grad drzave "+drzava+" je "+GeografijaDAO.glavniGrad(drzava).getNaziv());
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println(ispisiGradove());
        glavniGrad();
        GeografijaDAO.removeInstance();
    }
}