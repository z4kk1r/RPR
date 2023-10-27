package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite broj ili 'stop' za kraj");
        String input = new String();
        double suma = 0;
        int brojElemenata=0;
        List<Double> lista= new ArrayList<>();
        do {
            input = scanner.nextLine();
            double broj = 0;
            try {
                broj = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                if (!input.equalsIgnoreCase("stop")) {
                    System.out.println("Ponovite unos!");
                    continue;
                }
            }
            suma += broj;
            brojElemenata++;
            lista.add(broj);
        } while (!input.equalsIgnoreCase("stop"));
        double min=lista.get(0),max=lista.get(0);
        double std=0;
        double mean=suma/brojElemenata;
        for(int i=1;i<lista.size();i++){
            if(lista.get(i)>max){
                max=lista.get(i);
            }

            if(lista.get(i)<min){
                min=lista.get(i);
            }
            std+=Math.pow(lista.get(i)-mean,2);
        }
        std=Math.sqrt(std);
        System.out.printf("MIN: %.5f\n",min);
        System.out.printf("MAX: %.5f\n",max);
        System.out.printf("MEAN: %.5f\n",mean);
        System.out.printf("STD DEVIJACIJA: %.5f",std);

    }
}
