package ba.unsa.etf.rpr;

public class Racun {

    public long brojRacuna;
    public Osoba korisnikRacuna;
    public boolean odobrenjePrekoracenja;
    public double stanjeRacuna;

    Racun(long brojRacuna,Osoba o){
        this.brojRacuna = brojRacuna;
        this.korisnikRacuna=o;
        this.stanjeRacuna=0.0;
        this.odobrenjePrekoracenja=false;
    }

    public boolean izvrsiIsplatu(double iznos) {
        if (iznos > 0 && iznos <= stanjeRacuna) {
            stanjeRacuna -= iznos;
            return true;
        }
        else if(iznos<=0){
            return false;
        }
        else {
            return false;
        }
    }

    public boolean izvrsiUplatu(double iznos) {
        if (iznos > 0) {
            stanjeRacuna += iznos;
            return true;
        } else {
            return false;
        }
    }
}
