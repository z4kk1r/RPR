package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        KolekcijaImenaIPrezimena popis= new KolekcijaImenaIPrezimena();
        popis.dodajImeIPrezime("suljo", "suljic");
        popis.dodajImeIPrezime("mujo", "mujic");
        System.out.println(popis.getIndexNajduzegPara());
        System.out.println(popis.getImeIPrezime(1));
    }
}