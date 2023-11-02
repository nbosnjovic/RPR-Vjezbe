package ba.unsa.etf.rpr;

public class Osoba {

    private final String ime;
    private final String prezime;
    Osoba(String ime, String prezime){
        this.ime=ime;
        this.prezime=prezime;
    }

    @Override
    public String toString(){
        return ime + " "+ prezime;

    }
}
