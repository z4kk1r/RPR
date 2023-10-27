package ba.unsa.etf.rpr;

public class Faktorijel {
    public static int faktorijel(int n) {
        if (n == 1) return 1;
        return n * faktorijel(n - 1);
    }
}
