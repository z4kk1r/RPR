package ba.unsa.etf.rpr;
import java.util.*;
public class Main {
    public static void dodaj(Imenik imenik){
        Scanner ulaz= new Scanner(System.in);
        System.out.println("Korisnik: ");
        String korisnik= ulaz.nextLine();
        System.out.println("Vrsta (fiksni, mobilni, medjunarnodni): ");
        String vrsta= ulaz.nextLine();
        switch (vrsta){
            case "fiksni":{
                System.out.println("Pozivni: ");
                String pozivni= ulaz.nextLine();
                System.out.println("Broj: ");
                String broj= ulaz.nextLine();
                TelefonskiBroj t;
                t=new FiksniBroj(Grad.izPozivnog(pozivni),broj);
                imenik.dodaj(korisnik,t);
                        break;
            }
            case "mobilni":{
                System.out.println("Pozivni: ");
                int pozivni= ulaz.nextInt();
                System.out.println("Broj: ");
                ulaz.nextLine();
                String broj= ulaz.nextLine();
                TelefonskiBroj t;
                t=new MobilniBroj(pozivni,broj);
                imenik.dodaj(korisnik,t);
                break;
            }

            case "medjunarodni":{
                System.out.println("Drzava: ");
                String pozivni= ulaz.nextLine();
                System.out.println("Broj: ");
                String broj= ulaz.nextLine();
                TelefonskiBroj t;
                t=new MedunarodniBroj(MedBroj.izPozivnog(pozivni).toString(),broj);
                imenik.dodaj(korisnik,t);
                break;
            }
        };


        }

    public static void imeOsobe(Imenik imenik){
        System.out.println("Unesite ime osobe");
        Scanner ulaz= new Scanner(System.in);
        String ime=ulaz.nextLine();
        System.out.println(imenik.dajBroj(ime));
    }

    public static void brojOsobe(Imenik imenik){
        System.out.println("Vrsta broja (fiksni, mobilni, medjunarodni: )");
        Scanner ulaz= new Scanner(System.in);
        String vrsta=ulaz.nextLine();
        switch (vrsta){
            case "fiksni":{
                System.out.println("Pozivni: ");
                String pozivni= ulaz.nextLine();
                System.out.println("Broj: ");
                String broj= ulaz.nextLine();
                TelefonskiBroj t;
                t=new FiksniBroj(Grad.izPozivnog(pozivni),broj);
                System.out.println(imenik.dajIme(t));
                break;
            }

            case "mobilni":{
                System.out.println("Pozivni: ");
                int pozivni= ulaz.nextInt();
                System.out.println("Broj: ");
                ulaz.nextLine();
                String broj= ulaz.nextLine();
                TelefonskiBroj t;
                t=new MobilniBroj(pozivni,broj);
                System.out.println(imenik.dajIme(t));
                break;
            }

            case "medjunarodni":{
                System.out.println("Pozivni: ");
                String pozivni= ulaz.nextLine();
                System.out.println("Broj: ");
                String broj= ulaz.nextLine();
                TelefonskiBroj t;
                t=new MedunarodniBroj(MedBroj.izPozivnog(pozivni).toString(),broj);
                System.out.println(imenik.dajIme(t));
                break;
            }
        };
    }

    public static void brojeviNaSlovo(Imenik imenik,char slovo){
        System.out.println(imenik.naSlovo(slovo));
    }

    public  static void ljudiIzGrada(Imenik imenik,Grad g){
        Set<String> set=imenik.izGrada(g);
        for(String t2: set){
            System.out.println(t2);
        }
    }

    public static void brojeviIzGrada(Imenik imenik,Grad g){
        Set<TelefonskiBroj> set=imenik.izGradaBrojevi(g);
        for(TelefonskiBroj t2: set){
            System.out.println(t2.ispisi());
        }
    }
    public static void main(String[] args) {
        Imenik imenik= new Imenik();
        Scanner ulaz= new Scanner(System.in);
        int odabir=0;
        while(odabir!=7){
            System.out.println("Imenik: \n1 - Dodaj korisnika i broj \n2 - Ime osobe za telefonski broj \n3 - Broj za ime osobe \n4 - Brojevi osoboa cije ime pocinje na slovo \n5 - Imena osoba iz grada \n6 - Brojevi iz grada \n7 - Izlaz");
            odabir= ulaz.nextInt();
            switch (odabir){
                case 1:{
                    dodaj(imenik);
                    break;
                }
                case 2: {
                    imeOsobe(imenik);
                    break;
                }

                case 3: {
                    brojOsobe(imenik);
                    break;
                }

                case 4: {
                    System.out.println("Unesite slovo: ");
                    ulaz.nextLine();
                    char slovo=ulaz.next().charAt(0);
                    brojeviNaSlovo(imenik,slovo);
                    break;
                }

                case 5: {
                    System.out.println("Unesite grad: ");
                    ulaz.nextLine();
                    String grad=ulaz.nextLine().toUpperCase();
                    Grad g=Grad.valueOf(grad);
                    ljudiIzGrada(imenik,g);
                    break;
                }

                case 6: {
                    System.out.println("Unesite grad: ");
                    ulaz.nextLine();
                    String grad=ulaz.nextLine();
                    grad=grad.toUpperCase();
                    Grad g=Grad.valueOf(grad);
                    brojeviIzGrada(imenik,g);
                    break;
                }

                case 7: {
                    System.out.println("Hvala na koristenju imenika");
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("Unijeli ste pogresnu komandu!");
                }

            }
        }

    }
}