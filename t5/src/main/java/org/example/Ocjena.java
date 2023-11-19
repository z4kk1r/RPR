package org.example;

public class Ocjena {
    private LicneInformacije osoba;
    private int ocjena;

    public void setOcjena(int ocjena2) {
        if(ocjena2>0 && ocjena2<11)
        this.ocjena = ocjena2;
    }

    public Ocjena(LicneInformacije osoba, int x){
        this.ocjena=x;
        this.osoba=osoba;
    }

    public int getOcjena() {
        return ocjena;
    }

    public LicneInformacije getOsoba() {
        return osoba;
    }
}
