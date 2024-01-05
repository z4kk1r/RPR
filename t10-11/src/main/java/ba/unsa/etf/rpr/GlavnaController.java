package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class GlavnaController {
    private boolean zaustaviThread;
    public TableView TableViewGradovi;
    public TableColumn colGradId;
    public TableColumn colGradNaziv;
    public TableColumn colGradBrStanovnika;
    public TableColumn colGradDrzava;
    public Button btnDodajGrad;
    public Button btnDodajDrzavu;
    public Button btnIzmijeniGrad;
    public Button btnObrisiGrad;
    public ListView listaGradova;
    public Button btnRefresh;


    public void refreshTable(){
        TableViewGradovi.setItems(GeografijaDAO.glavniProzor());
    }

    @FXML
    public void initialize() throws SQLException {
        Thread t1= new Thread(()->{
            GeografijaDAO.resetujGrad();
        });

        Thread t2= new Thread(()->{
            GeografijaDAO.resetujDrzava();
        });

        t1.start();
        t2.start();

        Thread t3 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if(!zaustaviThread) {
                        Platform.runLater(() -> {
                            refreshTable();
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t3.start();
        this.refreshTable();
        //GeografijaDAO.vratiNaDefault();
        colGradId.setCellValueFactory(new PropertyValueFactory<>("idGrada"));
        colGradNaziv.setCellValueFactory(new PropertyValueFactory<>("nazivGrada"));
        colGradBrStanovnika.setCellValueFactory(new PropertyValueFactory<>("brojStanovnika"));
        colGradDrzava.setCellValueFactory(new PropertyValueFactory<>("nazivDrzave"));

        TableViewGradovi.setItems(GeografijaDAO.glavniProzor());
        System.out.println("Prikaz");
    }

    public void otvoriGradFormu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("grad.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        Stage stage=new Stage();
        stage.setTitle("Grad");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void otvoriDrzavaFormu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("drzava.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        Stage stage=new Stage();
        stage.setTitle("Drzava");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void obrisiGrad(ActionEvent event) {
        GradoviDrzava red= new GradoviDrzava(TableViewGradovi.getSelectionModel().getSelectedItem());
        GeografijaDAO.obrisiGrad(new Grad(red.getIdGrada(), "",0,0));
        refreshTable();
    }

    public void refresh(ActionEvent event) {
        refreshTable();
    }


    public void dragNaTable(MouseEvent mouseEvent) {
        this.zaustaviThread=true;
    }

    public void dragVanTable(MouseEvent mouseEvent) {
        this.zaustaviThread=false;
    }
}