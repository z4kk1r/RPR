package ba.unsa.etf.rpr;

public enum Broj {
    BHMOBILE0("60"),BHMOBILE1("61"),BHMOBILE2("62"),ERONET("63"),HALOO("64"),MTEL5("65"),
    MTEL6("66"),MTEL7("67");

    private final String name;

    private Broj(String name) {
        this.name = name;
    }

    public static Broj izPozivnog(String pozivni){
        for(Broj g: Broj.values()){
            if(g.name.equals(pozivni)) return g;
        }
        return null;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
