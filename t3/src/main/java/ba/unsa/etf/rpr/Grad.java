package ba.unsa.etf.rpr;

public enum Grad {
    SARAJEVO("033"),TRAVNIK("030"),ORASJE("031"),ZENICA("032"),LIVNO("034"),TUZLA("035"),
    MOSTAR("036"),BIHAC("037"),GORAZDE("038"),POSUSJE("039"),BRCKO("049"),BANJALUKA("051");

    private final String name;

    private Grad(String name) {
        this.name = name;
    }

    public static Grad izPozivnog(String pozivni){
        for(Grad g: Grad.values()){
            if(g.name.equals(pozivni)) return g;
        }
        return null;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
