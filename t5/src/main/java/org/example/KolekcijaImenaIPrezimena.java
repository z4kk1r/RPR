package org.example;

import java.util.ArrayList;

public class KolekcijaImenaIPrezimena implements NajduzeIMe{
    private ArrayList<String> imena;
    private ArrayList<String> prezimena;

    public KolekcijaImenaIPrezimena() {
        imena = new ArrayList<>();
        prezimena = new ArrayList<>();
    }

    public void dodajImeIPrezime(String ime, String prezime) {
        imena.add(ime);
        prezimena.add(prezime);
    }

    public int getIndexNajduzegPara() {
        int max = 0;
        int index = 0;
        for (int i = 0; i < imena.size(); i++) {
            if (imena.get(i).length() + prezimena.get(i).length() > max) {
                max = imena.get(i).length() + prezimena.get(i).length();
                index = i;
            }
        }
        return index;
    }

    public String getImeIPrezime(int i) {
        return new StringBuffer(imena.get(i)).append(" ").append(prezimena.get(i)).toString();
    }

    @Override
    public String getNajduzeIme() {
        return getImeIPrezime(getIndexNajduzegPara());
    }
}
