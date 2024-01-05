package ba.unsa.etf.rpr;


import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private ObservableList<Drzava> drzave= FXCollections.observableArrayList();
    private ObservableList<Grad> gradovi= FXCollections.observableArrayList();
    private static GeografijaDAO instance=null;
    private static Connection conn=connect();
    private static Connection connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database/baza.db");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void vratiNaDefault() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM Grad");
        stmt.executeUpdate("DELETE FROM Drzava");
        regenerisiBazu();
    }

    public synchronized static void resetujGrad(){
        System.out.println("Grad");
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Grad");
            stmt.execute("INSERT INTO grad VALUES (1,'Sarajevo',275000,2);");
            stmt.execute("INSERT INTO grad VALUES (2,'Zagreb',50000,3);");
            stmt.execute("INSERT INTO grad VALUES (3,'Beograd',275000,4);");
            stmt.execute("INSERT INTO grad VALUES (4,'London',2575000,1);");
            stmt.execute("INSERT INTO grad VALUES (5,'Pariz',4575000,null);");
            stmt.execute("INSERT INTO grad VALUES (6,'Ankara',3575000,null);");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static void resetujDrzava(){
        System.out.println("Drzava");
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Drzava");
            stmt.execute("INSERT INTO drzava VALUES (1,'Engleska','5');");
            stmt.execute("INSERT INTO drzava VALUES (2,'BiH','1');");
            stmt.execute("INSERT INTO drzava VALUES (3,'Hrvatska','2');");
            stmt.execute("INSERT INTO drzava VALUES (4,'Srbija','3');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer dajIdGrada(String grad){
        try {
            PreparedStatement p=conn.prepareStatement("Select id from grad where naziv=?");
            p.setString(1,grad);
            ResultSet rs=p.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Integer dajIdDrzave(String drzava){
        try {
            PreparedStatement p=conn.prepareStatement("Select id from drzava where naziv=?");
            p.setString(1,drzava);
            ResultSet rs=p.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    private static void regenerisiBazu(){
        Scanner ulaz=null;
        try{
            ulaz=new Scanner(new FileInputStream("database/baza_dump.sql"));
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


    public static ArrayList<ba.unsa.etf.rpr.Grad> gradovi(){
        try {
            ArrayList<ba.unsa.etf.rpr.Grad> gradovi=new ArrayList<>();
            PreparedStatement p1= conn.prepareStatement("Select * from grad order by broj_stanovnika desc");
            ResultSet result=p1.executeQuery();
            while (result.next()){
                gradovi.add(new ba.unsa.etf.rpr.Grad(result.getInt(1),result.getString(2),result.getInt(3),result.getInt(4)));
            }
            return gradovi;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            regenerisiBazu();
            gradovi();
        }
        return null;
    }

    public static ArrayList<ba.unsa.etf.rpr.Drzava> drzave(){
        try {
            ArrayList<ba.unsa.etf.rpr.Drzava> drzave=new ArrayList<>();
            PreparedStatement p1= conn.prepareStatement("Select * from drzava");
            ResultSet result=p1.executeQuery();
            while (result.next()){
                drzave.add(new ba.unsa.etf.rpr.Drzava(result.getInt(1),result.getString(2),result.getInt(3)));
            }
            return drzave;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            regenerisiBazu();
            drzave();
        }
        return null;
    }

    public static ba.unsa.etf.rpr.Grad glavniGrad(String drzava){
        try {
            ba.unsa.etf.rpr.Grad grad;
            PreparedStatement p1= conn.prepareStatement("SELECT grad.id, grad.naziv, broj_stanovnika,drzava\n" +
                    "FROM drzava,grad\n" +
                    "WHERE drzava.naziv=? AND drzava.glavni_grad=grad.id\n");
            p1.setString(1,drzava);
            ResultSet result=p1.executeQuery();
            result.next();
            grad=new ba.unsa.etf.rpr.Grad(result.getInt(1),result.getString(2),result.getInt(3),result.getInt(4));
            return grad;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            regenerisiBazu();
            glavniGrad(drzava);
        }
        return null;
    }

    public static void obrisiDrzavu(String drzava){
        try {
            ba.unsa.etf.rpr.Grad grad;
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
            System.out.println(e.getMessage());
            regenerisiBazu();
           obrisiDrzavu(drzava);
        }
    }

    public static void obrisiGrad(Grad grad){
        try {
            PreparedStatement p2= conn.prepareStatement("DELETE FROM grad\n" +
                    "WHERE id=?\n");
            p2.setInt(1,grad.getId());
            p2.execute();

            p2= conn.prepareStatement("update drzava\n" +
                    "set glavni_grad=null WHERE glavni_grad=?\n");
            p2.setInt(1,grad.getId());
            p2.execute();
            System.out.println("Uspjesno executano");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            regenerisiBazu();
            obrisiGrad(grad);
        }
    }

    public static Grad dodajGrad(ba.unsa.etf.rpr.Grad grad){
        try {
            PreparedStatement noviID=conn.prepareStatement("select max(id)+1 from grad");
            ResultSet rs=noviID.executeQuery();
            if(rs.next())
             grad.setId(rs.getInt(1));
            else grad.setId(1);
            PreparedStatement p1;
            if(grad.getDrzava()==-1){
                p1= conn.prepareStatement("INSERT INTO grad VALUES(?,?,?,null)\n");
                p1.setInt(1,grad.getId());
                p1.setString(2,grad.getNaziv());
                p1.setInt(3,grad.getBroj_stanovnika());
            }else{
                p1= conn.prepareStatement("INSERT INTO grad VALUES(?,?,?,?)\n");
                p1.setInt(1,grad.getId());
                p1.setString(2,grad.getNaziv());
                p1.setInt(3,grad.getBroj_stanovnika());
                p1.setInt(4,grad.getDrzava());
            }
            p1.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            regenerisiBazu();
            dodajGrad(grad);
        }
        return grad;
    }

    public static Drzava dodajDrzavu(ba.unsa.etf.rpr.Drzava drzava){
        try {
            PreparedStatement noviID=conn.prepareStatement("select max(id)+1 from drzava");
            ResultSet rs=noviID.executeQuery();
            if(rs.next())
             drzava.setId(rs.getInt(1));
            else  drzava.setId(1);
            PreparedStatement p1;
            if(drzava.getGlavni_grad()==-1){
                p1=conn.prepareStatement("INSERT INTO drzava VALUES(?,?,null)\n");
                p1.setInt(1,drzava.getId());
                p1.setString(2,drzava.getNaziv());
            }else{
                 p1= conn.prepareStatement("INSERT INTO drzava VALUES(?,?,?)\n");
                p1.setInt(1,drzava.getId());
                p1.setString(2,drzava.getNaziv());
                p1.setInt(3,drzava.getGlavni_grad());
            }
            p1.execute();
            System.out.println("Uspjesno executano");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            regenerisiBazu();
            dodajDrzavu(drzava);
        }
        return drzava;
    }

    public static void dodajDrzavuId(int g,int id){
        try {
            PreparedStatement p=conn.prepareStatement("Update grad set drzava=? where id=?");
            p.setInt(1,id);
            p.setInt(2,g);
            p.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void izmijeniGrad(ba.unsa.etf.rpr.Grad grad){
        try {
            PreparedStatement p1= conn.prepareStatement("UPDATE grad\n" +
                    "SET naziv=?,broj_stanovnika=?, drzava=?\n" +
                    "WHERE id=?\n");
            p1.setString(1, grad.getNaziv());
            p1.setInt(2,grad.getBroj_stanovnika());
            p1.setInt(3,grad.getDrzava());
            p1.setInt(4,grad.getId());
            p1.execute();
            System.out.println("Uspjesno executano");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            regenerisiBazu();
            izmijeniGrad(grad);
        }
    }

    public static ba.unsa.etf.rpr.Drzava nadjiDrzavu(String drzava){
        try {
            ba.unsa.etf.rpr.Drzava dr;
            PreparedStatement p1= conn.prepareStatement("SELECT *\n" +
                    "FROM drzava \n" +
                    "WHERE naziv=? \n");
            p1.setString(1,drzava);
            ResultSet result=p1.executeQuery();
            result.next();
            if(result.getFetchSize()>1 || result.getFetchSize()<1) {System.out.println(result.getFetchSize());return null;}
            dr=new ba.unsa.etf.rpr.Drzava(result.getInt(1),result.getString(2),result.getInt(3));
            return dr;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            regenerisiBazu();
            nadjiDrzavu(drzava);
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
            System.out.println(e.getMessage());
            regenerisiBazu();
            dajDrzavuPrekoGrada(grad);
        }
        return null;
    }

    public static ObservableList<Grad> sviGradovi(){
        ObservableList<Grad> gradovi= FXCollections.observableArrayList();
        try {
            PreparedStatement p=conn.prepareStatement("Select * from grad where drzava is null");
            ResultSet rs=p.executeQuery();
            while (rs.next()){
                gradovi.add(new Grad(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getInt(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gradovi;
    }

    public static ObservableList<Drzava> sveDrzave(){
        ObservableList<Drzava> drzave= FXCollections.observableArrayList();
        try {
            PreparedStatement p=conn.prepareStatement("Select * from drzava");
            ResultSet rs=p.executeQuery();
            while (rs.next()){
                drzave.add(new Drzava(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return drzave;
    }

    public static ObservableList<GradoviDrzava> glavniProzor(){
        ObservableList<GradoviDrzava> gradoviDrzave= FXCollections.observableArrayList();
        try {
            PreparedStatement p=conn.prepareStatement("Select g.id, g.naziv, g.broj_stanovnika, d.naziv from grad g, drzava d where g.drzava=d.id");
            ResultSet rs=p.executeQuery();
            while (rs.next()){
                gradoviDrzave.add(new GradoviDrzava(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return gradoviDrzave;
    }

    public static GeografijaDAO getInstance() throws ClassNotFoundException {
        if(instance==null) return new GeografijaDAO();
        return instance;
    }

    public static void removeInstance() throws SQLException {
        conn.close();
        instance=null;
    }

    public ObservableList<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(ObservableList<Drzava> drzave) {
        this.drzave = drzave;
    }

    public ObservableList<Grad> getGradovi() {
        return gradovi;
    }

    public void setGradovi(ObservableList<Grad> gradovi) {
        this.gradovi = gradovi;
    }

}
