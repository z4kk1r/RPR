package org.example;

public class InformacijeOStudentu extends LicneInformacije implements Predstavi,MozeOcijeniti{
    private String ime;
    private String prezime;
    private String godinaStudija;
    private String brojIndexa;

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

    public String getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(String godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public String getBrojIndexa() {
        return brojIndexa;
    }

    public void setBrojIndexa(String brojIndexa) {
        this.brojIndexa = brojIndexa;
    }

    @Override
    public String predstavi() {
        return new StringBuffer(ime).append(" "+prezime).append(" "+brojIndexa).append(" "+godinaStudija).toString();
    }


    @Override
    public void ocijeniNastavnika(InformacijeONastavniku nastavnik, int x) {
        nastavnik.dodajOcjenu(new Ocjena(this,x));
    }

    @Override
    public void ocijeniPredmet(Predmet predmet, int x) {
        predmet.dodajOcjenu(new Ocjena(this,x));
    }
}
