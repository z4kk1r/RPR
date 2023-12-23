package ba.unsa.etf.rpr;

import oracle.ucp.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;

public class GeografijaDAO {
    private static GeografijaDAO instance=null;
    private Connection conn;
    private GeografijaDAO() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        String url="jdbc:oracle:thin:@ora.db.lab.ri.etf.unsa.ba:1521:ETFLAB";
        try {
            conn = DriverManager.getConnection(url, "XXXXXXX", "XXXXXXXX");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Grad> gradovi(){
        try {
            ArrayList<Grad> gradovi=new ArrayList<>();
            PreparedStatement p1= conn.prepareStatement("Select * from grad order by broj_stanovnika desc");
            ResultSet result=p1.executeQuery();
            while (result.next()){
                gradovi.add(new Grad(result));
            }
            return gradovi;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Grad glavniGrad(String drzava){
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
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void obrisiDrzavu(String drzava){
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
            System.out.println(e.getMessage());
        }
    }

    public void dodajGrad(Grad grad){
        try {
            PreparedStatement p1= conn.prepareStatement("INSERT INTO grad VALUES(?,?,?,?)\n");
            p1.setInt(1,grad.getId());
            p1.setString(2,grad.getNaziv());
            p1.setInt(3,grad.getBroj_stanovnika());
            p1.setInt(4,grad.getDrzava());
            p1.execute();
            System.out.println("Uspjesno executano");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dodajDrzavu(Drzava drzava){
        try {
            PreparedStatement p1= conn.prepareStatement("INSERT INTO drzava VALUES(?,?,?)\n");
            p1.setInt(1,drzava.getId());
            p1.setString(2,drzava.getNaziv());
            p1.setInt(3,drzava.getGlavni_grad());
            p1.execute();
            System.out.println("Uspjesno executano");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void izmijeniGrad(Grad grad){
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
            System.out.println(e.getMessage());
        }
    }

    public Drzava nadjiDrzavu(String drzava){
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
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String dajDrzavuPrekoGrada(String grad){
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
        }
        return null;
    }

    public static GeografijaDAO getInstance() throws ClassNotFoundException {
        if(instance==null) return new GeografijaDAO();
        return instance;
    }

    public static void removeInstance() throws SQLException {
        instance.conn.close();
        instance=null;
    }
}
