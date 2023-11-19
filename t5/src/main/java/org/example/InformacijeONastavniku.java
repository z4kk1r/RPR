package org.example;

import java.util.ArrayList;

public class InformacijeONastavniku extends LicneInformacije implements Predstavi,MozeOcijeniti{
    private String ime;
    private String prezime;
    private String titula;
    private ArrayList<Ocjena>ocjene;
    public InformacijeONastavniku(){
        ocjene= new ArrayList<>();
    }

    @Override
    public String getIme() {
        return ime;
    }

    @Override
    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String getPrezime() {
        return prezime;
    }

    @Override
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    @Override
    public String predstavi() {
        return new StringBuffer(ime).append(" "+prezime).append(" "+titula).toString();
    }

    @Override
    public void ocijeniNastavnika(InformacijeONastavniku nastavniku, int x) {
        throw new RuntimeException("Nastavnik ne moze ocijeniti nastavnika");
    }

    @Override
    public void ocijeniPredmet(Predmet predmet, int x) {
        predmet.dodajOcjenu(new Ocjena(this,x));
    }

    public void dodajOcjenu(Ocjena x){
        ocjene.add(x);
    }

    public double dajOcjenuZaNastavnika(){
        double ocjena=0;
        for(int i=0;i<ocjene.size();i++){
            ocjena+=ocjene.get(i).getOcjena();
        }
        return ocjena/ocjene.size();
    }

}
