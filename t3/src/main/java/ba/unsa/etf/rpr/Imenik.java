package ba.unsa.etf.rpr;

import java.util.*;
public class Imenik {
    private Map<String, TelefonskiBroj> imenik;

    public Imenik(){
        imenik=new HashMap<String,TelefonskiBroj>();
    }
    public void dodaj(String ime, TelefonskiBroj broj) {
        imenik.put(ime, broj);
    }

    public String dajBroj(String ime) {
        TelefonskiBroj broj = imenik.get(ime);
        if (broj != null) return broj.ispisi();
        return null;
    }

    public String dajIme(TelefonskiBroj broj) {
        //System.out.println(broj.ispisi());
        for(Map.Entry<String,TelefonskiBroj> entry : imenik.entrySet()){
            if(entry.getValue().ispisi().equals(broj.ispisi())){
                return entry.getKey();
            }
        }
        return null;
    }

    public String naSlovo(char s){
        StringBuffer ispis=new StringBuffer();
        for(Map.Entry<String,TelefonskiBroj> entry: imenik.entrySet()){
            if(entry.getKey().toUpperCase().charAt(0)==s || entry.getKey().toLowerCase().charAt(0)==s){
                ispis.append(entry.getKey()+" - "+entry.getValue().ispisi()+"\n");
            }
        }
        return ispis.toString();
    }

    public Set<String> izGrada(Grad g){
        Set<String> set= new TreeSet<String>();
        for(Map.Entry<String,TelefonskiBroj> entry: imenik.entrySet()){
            if(entry.getValue().ispisi().startsWith(g.toString())){
                set.add(entry.getKey());
            }
        }
        return set;
    }

    public Set<TelefonskiBroj> izGradaBrojevi(Grad g){
        Set<TelefonskiBroj> set= new TreeSet<TelefonskiBroj>(
                new Comparator<TelefonskiBroj>() {
                    @Override
                    public int compare(TelefonskiBroj o1, TelefonskiBroj o2) {
                        return o1.ispisi().compareTo(o2.ispisi());
                    }
                }
        );
        for(Map.Entry<String,TelefonskiBroj> entry: imenik.entrySet()){
            if(entry.getValue().ispisi().startsWith(g.toString())){
                set.add(entry.getValue());
            }
        }
        return set;
    }
}
