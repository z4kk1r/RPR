package org.example;

public class Pobjednik {
    private String ime;
    private String prezime;
    private int brojZnakova;
    private NajduzeIMe popis;

    public Pobjednik(NajduzeIMe popis){
        String imePrezime=popis.getNajduzeIme();
        ime=imePrezime.substring(0,imePrezime.indexOf(" "));
        prezime=imePrezime.substring(imePrezime.indexOf(" ")+1);
        brojZnakova=ime.length();
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getBrojZnakova() {
        return brojZnakova;
    }
}
