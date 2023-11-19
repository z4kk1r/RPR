package org.example;

import java.util.ArrayList;

public class KolekcijaPoruka {
    private ArrayList<String>poruke;
    public ArrayList<String> getPoruke(){
        return poruke;
    }
    public KolekcijaPoruka(ArrayList<? extends Predstavi>lista ){
        poruke= new ArrayList<String>();
        for(int i=0; i<lista.size();i++){
            poruke.add(lista.get(i).predstavi());
        }
    }
}
