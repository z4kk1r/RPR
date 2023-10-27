package ba.unsa.etf.rpr;

public class Sinus {

    public static double sinus(double x) {
        double rez = 0;
        for (int i = 0; i <= 10; i++) {
            rez += (Math.pow(-1, i) * Math.pow(x, 2 * i + 1)) / Faktorijel.faktorijel(2 * i + 1);
        }
        return rez;
    }
}
