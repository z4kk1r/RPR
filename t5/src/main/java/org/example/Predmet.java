package org.example;

import java.util.ArrayList;

public class Predmet implements Predstavi{
    private String naziv;
    private String opis;
    private ArrayList<Ocjena>ocjene;
    public Predmet(){
        naziv=null;
        opis=null;
        ocjene=new ArrayList<Ocjena>();

    }
    @Override
    public String predstavi() {
        return new StringBuffer(naziv).append(" "+opis).toString();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void dodajOcjenu(Ocjena x){
        ocjene.add(x);
    }

    public double dajOcjenuZaPredmet(){
        double ocjena=0;
        for(int i=0;i<ocjene.size();i++){
            ocjena+=ocjene.get(i).getOcjena();
        }
        return ocjena/ocjene.size();
    }
}
