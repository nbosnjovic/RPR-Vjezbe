package ba.unsa.etf.rpr;
import java.util.ArrayList;
import java.util.List;

public class InformacijeONastavniku extends LicneInformacije implements MozeOcijeniti {
    private String titula;
    private List<Ocjena> ocjene;
    public InformacijeONastavniku(String ime, String prezime, String titula) {
        super(ime, prezime);
        this.titula = titula;
        ocjene=new ArrayList<>();
    }
    @Override
    public Ocjena ocijeni(int ocjena) {
        Ocjena novaOcjena = new Ocjena(null, ocjena);
        ocjene.add(novaOcjena);
        return novaOcjena;
    }
    public List<Ocjena> getOcjene() {
        return ocjene;
    }

    public void setOcjene(List<Ocjena> ocjene) {
        this.ocjene = ocjene;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    public String predstavi() {
        return super.predstavi() + ", Titula: " + titula;
    }
}
