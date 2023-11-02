package ba.unsa.etf.rpr;

    public class Main {

        public static void main(String[] args) {
            Osoba osoba = new Osoba("Naida", "B");
            Racun racun = new Racun(123456, osoba);
            Korisnik korisnik = new Korisnik("hedhb", "djnk");
            korisnik.dodajRacun(racun);

            System.out.println("Osoba: " + osoba);
            System.out.println("Broj racuna: " + racun.brojRacuna);
            System.out.println("Stanje racuna: " + racun.stanjeRacuna);

            double uplata = 100.0;

            if (racun.izvrsiUplatu(uplata)) {
                System.out.println("Uplata je uspjesna. Novo stanje racuna: " + racun.stanjeRacuna);
            } else {
                System.out.println("Uplata nije uspjela. Nema dovoljno sredstava.");
            }

            double isplata = 50.0;

            if (racun.izvrsiIsplatu(isplata)) {
                System.out.println("Isplata je uspjesna. Novo stanje racuna: " + racun.stanjeRacuna);
            } else {
                System.out.println("Isplata nije uspjela. Nema dovoljno sredstava.");
            }
        }
        }


