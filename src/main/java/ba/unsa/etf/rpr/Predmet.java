package ba.unsa.etf.rpr;
import java.util.ArrayList;
import java.util.List;

public class Predmet  implements MozeOcijeniti{
    private String naziv;
    private String opis;
    private List<Ocjena> ocjene;

    public Predmet(String naziv, String opis) {
        this.naziv = naziv;
        this.opis = opis;
        this.ocjene = new ArrayList<>();
    }

    @Override
    public Ocjena ocijeni(int ocjena) {
        Ocjena novaOcjena = new Ocjena(null, ocjena);
        ocjene.add(novaOcjena);
        return novaOcjena;
    }

    // Get metoda za ocjene
    public List<Ocjena> getOcjene() {
        return ocjene;
    }
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    public String predstavi() {
        return "Naziv predmeta: " + naziv + ", Opis predmeta: " + opis;
    }
}
