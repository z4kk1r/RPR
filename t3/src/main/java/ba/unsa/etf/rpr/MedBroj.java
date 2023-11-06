package ba.unsa.etf.rpr;

public enum MedBroj {
    BIH("+387"),SRBIJA("+381"),HRVATSKA("+385"),NJEMACKA("+49");

    private final String name;

    private MedBroj(String name) {
        this.name = name;
    }

    public static MedBroj izPozivnog(String pozivni){
        for(MedBroj g: MedBroj.values()){
            if(g.name.equals(pozivni)) return g;
        }
        return null;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
