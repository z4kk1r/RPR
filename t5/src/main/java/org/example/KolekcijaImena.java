package org.example;

import java.util.ArrayList;

public class KolekcijaImena implements NajduzeIMe{
    private ArrayList<String> popis;
    public KolekcijaImena(){
        popis= new ArrayList<>();
    }

    public void dodajIme(String ime){
        popis.add(ime);
    }

    public String getNajduzeIme(){
        String max="";
        for(String x: popis){
            if(x.length()>max.length()){
                max=x;
            }
        }
        return max;
    }
}
