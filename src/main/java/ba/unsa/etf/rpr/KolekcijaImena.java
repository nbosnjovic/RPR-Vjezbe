package ba.unsa.etf.rpr;
import java.util.List;

    public class KolekcijaImena {
        private List<String> imena;

        public KolekcijaImena(List<String> imena) {
            this.imena = imena;
        }

        public List<String> getImena() {
            return imena;
        }

        public String getNajduzeIme() {
            String najduzeIme = "";
            for (String imePrezime : imena) {
                String[] dijelovi = imePrezime.split(" ");
                String ime = dijelovi[0];
                if (ime.length() > najduzeIme.length()) {
                    najduzeIme = ime;
                }
            }
            return najduzeIme;
        }

    }
