package ba.unsa.etf.rpr;
public class Grad {
    private int id;
    private String naziv;
    private int brojStanovnika;
    private String drzava;

    public Grad(int id, String naziv, int brojStanovnika, String drzava) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = drzava;
    }

    public Grad(int id, String naziv, int brojStanovnika) {
        this(id, naziv, brojStanovnika, null);
    }
    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public String getDrzava() { return drzava;}
}
