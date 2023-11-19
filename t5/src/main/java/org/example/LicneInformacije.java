package org.example;

public abstract class LicneInformacije implements Predstavi{
    private String ime;
    private String prezime;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String predstavi() {
        return new StringBuffer(ime).append(" "+prezime).toString();
    }
}
