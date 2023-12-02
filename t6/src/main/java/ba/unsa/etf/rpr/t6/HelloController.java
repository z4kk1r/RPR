package ba.unsa.etf.rpr.t6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;

public class HelloController {
    @FXML
    private TextField result;
    @FXML
    private GridPane gridPane;

    @FXML
    private Button nula;

    @FXML
    private Button jedan;

    @FXML
    private Button dva;

    @FXML
    private Button tri;

    @FXML
    private Button cetiri;

    @FXML
    private Button pet;

    @FXML
    private Button sest;

    @FXML
    private Button sedam;

    @FXML
    private Button osam;

    @FXML
    private Button devet;

    @FXML
    private Button jednako;

    @FXML
    private Button add;

    @FXML
    private Button sub;

    @FXML
    private Button mul;

    @FXML
    private Button div;

    @FXML
    private Button tacka;

    private double prviOp;
    private int operacija;
    private boolean test=true;



    @FXML
    void initialize() {
        for (int rowIndex = 0; rowIndex < gridPane.getRowCount(); rowIndex++) {
            for (int colIndex = 0; colIndex < gridPane.getColumnCount(); colIndex++) {
                Button button = (Button) gridPane.getChildren().get(rowIndex * gridPane.getColumnCount() + colIndex);

                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setFillWidth(button, true);
                GridPane.setFillHeight(button, true);
            }
        }
    }

    private String formatResult(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.######"); // Change the pattern as needed
        return decimalFormat.format(value).replaceAll("0*$", "").replaceAll("\\.$", "");
    }

    public void onHelloButtonClick(ActionEvent event) {
        Button source = (Button) event.getSource();
        if (jedan.equals(source)) {
            if(result.getText().startsWith("0") && !result.getText().startsWith("0.")){
                result.setText("1");
            }else
            result.setText(result.getText()+"1");
        } else if (dva.equals(source)) {
            if(result.getText().startsWith("0")&& !result.getText().startsWith("0.")){
                result.setText("2");
            }else
                result.setText(result.getText()+"2");

        } else if (tri.equals(source)) {
            if(result.getText().startsWith("0")&& !result.getText().startsWith("0.")){
                result.setText("3");
            }else
                result.setText(result.getText()+"3");

        } else if (cetiri.equals(source)) {
            if(result.getText().startsWith("0")&& !result.getText().startsWith("0.")){
                result.setText("4");
            }else
                result.setText(result.getText()+"4");

        } else if (pet.equals(source)) {
            if(result.getText().startsWith("0")&& !result.getText().startsWith("0.")){
                result.setText("5");
            }else
                result.setText(result.getText()+"5");

        } else if (sest.equals(source)) {
            if(result.getText().startsWith("0")&& !result.getText().startsWith("0.")){
                result.setText("6");
            }else
                result.setText(result.getText()+"6");

        } else if (sedam.equals(source)) {
            if(result.getText().startsWith("0")&& !result.getText().startsWith("0.")){
                result.setText("7");
            }else
                result.setText(result.getText()+"7");

        } else if (osam.equals(source)) {
            if(result.getText().startsWith("0")&& !result.getText().startsWith("0.")){
                result.setText("8");
            }else
                result.setText(result.getText()+"8");

        } else if (devet.equals(source)) {
            if(result.getText().startsWith("0")&& !result.getText().startsWith("0.")){
                result.setText("9");
            }else
                result.setText(result.getText()+"9");

        } else if (nula.equals(source)) {
            if(result.getText().startsWith("0")&& !result.getText().startsWith("0.")) {
                result.setText("0");
            }
            else{
                result.setText(result.getText()+"0");
            }
        } else if (tacka.equals(source)) {
            result.setText(result.getText()+".");

        } else if (add.equals(source)) {
            prviOp=Double.parseDouble(result.getText());
            result.setText("0");
            operacija=1;
            test=true;

        } else if (sub.equals(source)) {
            prviOp=Double.parseDouble(result.getText());
            result.setText("0");
            operacija=2;
            test=true;

        } else if (mul.equals(source)) {
            prviOp=Double.parseDouble(result.getText());
            result.setText("0");
            operacija=3;
            test=true;

        } else if (div.equals(source)) {
            prviOp=Double.parseDouble(result.getText());
            result.setText("0");
            operacija=4;
            test=true;

        } else if (jednako.equals(source)) {
            double drugiOp=Double.parseDouble(result.getText());
            if(test){
                double temp=prviOp;
                prviOp=drugiOp;
                drugiOp=temp;
                test=false;
            }
            if(result.getText().length()!=0){
                if(operacija==1){
                    result.setText(String.valueOf(prviOp+drugiOp));
                } else if (operacija==2) {
                    result.setText(String.valueOf(drugiOp-prviOp));
                }
                else if (operacija==3) {
                    result.setText(String.valueOf(prviOp*drugiOp));
                }
                else if (operacija==4) {
                    if(prviOp==0){
                        result.setText("ERROR!");
                    }else {
                        result.setText(String.valueOf(drugiOp / prviOp));
                    }
                }
            }
        }
    }

    public void brisi(KeyEvent keyEvent) {
        if(keyEvent.isShiftDown()){
            result.deleteText(0,result.getText().length());
        }
    }
}