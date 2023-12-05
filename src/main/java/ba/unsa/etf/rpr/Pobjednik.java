package ba.unsa.etf.rpr;

import java.util.List;

public class Pobjednik {
    private String ime;
    private String prezime;

    int brojZnakova;
    private KolekcijaImena kolekcija;

    public Pobjednik(KolekcijaImena kolekcijaImena) {
        String najduzeIme = kolekcijaImena.getNajduzeIme();
        String najduzeImePrezime = "";
        for (String imePrezime : kolekcijaImena.getImena()) {
            String[] dijelovi = imePrezime.split(" ");
            String ime = dijelovi[0];  // Prvo ime
            if (ime.equals(najduzeIme)) {
                najduzeImePrezime = imePrezime;
                break;
            }
        }

        String[] dijeloviNajduzegImena = najduzeImePrezime.split(" ");
        this.ime = dijeloviNajduzegImena[0];
        this.prezime = dijeloviNajduzegImena.length > 1 ? dijeloviNajduzegImena[1] : "";
        this.brojZnakova = najduzeIme.length();
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

    public String predstavi() {
        return "Pobjednik: " + ime + " " + prezime + ", Broj znakova: " + brojZnakova;
    }
}