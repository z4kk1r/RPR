package ba.unsa.etf.rpr;

import oracle.ucp.proxy.annotation.Pre;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance=null;
    private static Connection conn=connect();
    private static Connection connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza2.db");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void vratiNaDefault() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM Grad");
        stmt.executeUpdate("DELETE FROM Drzava");
        regenerisiBazu();
    }


    private static void regenerisiBazu(){
        Scanner ulaz=null;
        try{
            ulaz=new Scanner(new FileInputStream("baza_dump.sql"));
            String upit="";
            while(ulaz.hasNext()){
                upit+=ulaz.nextLine();
                if(upit.length()>1 && upit.charAt(upit.length()-1)==';'){
                    try{
                        PreparedStatement p= conn.prepareStatement(upit);
                        p.execute();
                        upit="";
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            ulaz.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


    public static ArrayList<Grad> gradovi(){
        try {
            ArrayList<Grad> gradovi=new ArrayList<>();
            PreparedStatement p1= conn.prepareStatement("Select * from grad order by broj_stanovnika desc");
            ResultSet result=p1.executeQuery();
            while (result.next()){
                gradovi.add(new Grad(result));
            }
            return gradovi;
        } catch (SQLException e) {
            regenerisiBazu();
            try{
                ArrayList<Grad> gradovi=new ArrayList<>();
                PreparedStatement p1= conn.prepareStatement("Select * from grad order by broj_stanovnika desc");
                ResultSet result=p1.executeQuery();
                while (result.next()){
                    gradovi.add(new Grad(result));
                }
                return gradovi;
            }catch (SQLException e1){
                System.out.println(e1.getMessage());
            }
        }
        return null;
    }

    public static Grad glavniGrad(String drzava){
        try {
            Grad grad;
            PreparedStatement p1= conn.prepareStatement("SELECT grad.id, grad.naziv, broj_stanovnika,drzava\n" +
                    "FROM drzava,grad\n" +
                    "WHERE drzava.naziv=? AND drzava.glavni_grad=grad.id\n");
            p1.setString(1,drzava);
            ResultSet result=p1.executeQuery();
            result.next();
            grad=new Grad(result);
            return grad;
        } catch (SQLException e) {
            regenerisiBazu();
            try{
                Grad grad;
                PreparedStatement p1= conn.prepareStatement("SELECT grad.id, grad.naziv, broj_stanovnika,drzava\n" +
                        "FROM drzava,grad\n" +
                        "WHERE drzava.naziv=? AND drzava.glavni_grad=grad.id\n");
                p1.setString(1,drzava);
                ResultSet result=p1.executeQuery();
                result.next();
                grad=new Grad(result);
                return grad;
            }catch(SQLException e1) {
                System.out.println(e1.getMessage());
            }
        }
        return null;
    }

    public static void obrisiDrzavu(String drzava){
        try {
            Grad grad;
            PreparedStatement p1= conn.prepareStatement("SELECT id\n" +
                    "FROM drzava \n" +
                    "WHERE naziv=? \n");
            p1.setString(1,drzava);
            ResultSet result=p1.executeQuery();
            result.next();
            int id_drzave=result.getInt(1);

            PreparedStatement p2= conn.prepareStatement("DELETE FROM drzava\n" +
                    "WHERE id=?\n");
            p2.setInt(1,id_drzave);
            p2.execute();
            System.out.println("Uspjesno executano");
        } catch (SQLException e) {
            regenerisiBazu();
            try{
                Grad grad;
                PreparedStatement p1= conn.prepareStatement("SELECT id\n" +
                        "FROM drzava \n" +
                        "WHERE naziv=? \n");
                p1.setString(1,drzava);
                ResultSet result=p1.executeQuery();
                result.next();
                int id_drzave=result.getInt(1);

                PreparedStatement p2= conn.prepareStatement("DELETE FROM drzava\n" +
                        "WHERE id=?\n");
                p2.setInt(1,id_drzave);
                p2.execute();
                System.out.println("Uspjesno executano");
            }catch(SQLException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }

    public static void dodajGrad(Grad grad){
        try {
            PreparedStatement p1= conn.prepareStatement("INSERT INTO grad VALUES(?,?,?,?)\n");
            p1.setInt(1,grad.getId());
            p1.setString(2,grad.getNaziv());
            p1.setInt(3,grad.getBroj_stanovnika());
            p1.setInt(4,grad.getDrzava());
            p1.execute();
            System.out.println("Uspjesno executano");
        } catch (SQLException e) {
            regenerisiBazu();
            try{
                PreparedStatement p1= conn.prepareStatement("INSERT INTO grad VALUES(?,?,?,?)\n");
                p1.setInt(1,grad.getId());
                p1.setString(2,grad.getNaziv());
                p1.setInt(3,grad.getBroj_stanovnika());
                p1.setInt(4,grad.getDrzava());
                p1.execute();
                System.out.println("Uspjesno executano");
            }catch(SQLException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }

    public static void dodajDrzavu(Drzava drzava){
        try {
            PreparedStatement p1= conn.prepareStatement("INSERT INTO drzava VALUES(?,?,?)\n");
            p1.setInt(1,drzava.getId());
            p1.setString(2,drzava.getNaziv());
            p1.setInt(3,drzava.getGlavni_grad());
            p1.execute();
            System.out.println("Uspjesno executano");
        } catch (SQLException e) {
            regenerisiBazu();
            try{
                PreparedStatement p1= conn.prepareStatement("INSERT INTO drzava VALUES(?,?,?)\n");
                p1.setInt(1,drzava.getId());
                p1.setString(2,drzava.getNaziv());
                p1.setInt(3,drzava.getGlavni_grad());
                p1.execute();
                System.out.println("Uspjesno executano");
            }catch(SQLException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }

    public static void izmijeniGrad(Grad grad){
        try {
            PreparedStatement p1= conn.prepareStatement("UPDATE grad\n" +
                    "SET naziv=?,broj_stanovnika=?\n" +
                    "WHERE id=?\n");
            p1.setString(1, grad.getNaziv());
            p1.setInt(2,grad.getBroj_stanovnika());
            p1.setInt(3,grad.getId());
            p1.execute();
            System.out.println("Uspjesno executano");
        } catch (SQLException e) {
            regenerisiBazu();
            try{
                PreparedStatement p1= conn.prepareStatement("UPDATE grad\n" +
                        "SET naziv=?,broj_stanovnika=?\n" +
                        "WHERE id=?\n");
                p1.setString(1, grad.getNaziv());
                p1.setInt(2,grad.getBroj_stanovnika());
                p1.setInt(3,grad.getId());
                p1.execute();
                System.out.println("Uspjesno executano");
            }catch(SQLException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }

    public static Drzava nadjiDrzavu(String drzava){
        try {
            Drzava dr;
            PreparedStatement p1= conn.prepareStatement("SELECT *\n" +
                    "FROM drzava \n" +
                    "WHERE naziv=? \n");
            p1.setString(1,drzava);
            ResultSet result=p1.executeQuery();
            result.next();
            if(result.getFetchSize()!=1) return null;
            dr=new Drzava(result);
            return dr;
        } catch (SQLException e) {
            regenerisiBazu();
            try{
                Drzava dr;
                PreparedStatement p1= conn.prepareStatement("SELECT *\n" +
                        "FROM drzava \n" +
                        "WHERE naziv=? \n");
                p1.setString(1,drzava);
                ResultSet result=p1.executeQuery();
                result.next();
                if(result.getFetchSize()!=1) return null;
                dr=new Drzava(result);
                return dr;
            }catch(SQLException e1) {
                System.out.println(e1.getMessage());
            }
        }
        return null;
    }

    public static String dajDrzavuPrekoGrada(String grad){
        try {
            String dr;
            PreparedStatement p1= conn.prepareStatement("SELECT d.naziv\n" +
                    "FROM drzava d, grad g\n" +
                    "WHERE g.drzava=d.id AND g.naziv=?\n");
            p1.setString(1,grad);
            ResultSet result=p1.executeQuery();
            result.next();
            dr=result.getString(1);
            return dr;
        } catch (SQLException e) {
            regenerisiBazu();
            try{
                String dr;
                PreparedStatement p1= conn.prepareStatement("SELECT d.naziv\n" +
                        "FROM drzava d, grad g\n" +
                        "WHERE g.drzava=d.id AND g.naziv=?\n");
                p1.setString(1,grad);
                ResultSet result=p1.executeQuery();
                result.next();
                dr=result.getString(1);
                return dr;
            }catch(SQLException e1) {
                System.out.println(e1.getMessage());
            }
        }
        return null;
    }

    public static GeografijaDAO getInstance() throws ClassNotFoundException {
        if(instance==null) return new GeografijaDAO();
        return instance;
    }

    public static void removeInstance() throws SQLException {
        conn.close();
        instance=null;
    }
}
