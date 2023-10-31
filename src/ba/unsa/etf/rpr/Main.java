package ba.unsa.etf.rpr;

public class Main {

    public static void main(String[] args) {
        Sat s = new Sat (1,9,33);
        s.Ispisi();
        s.Sljedeci();
        s.Ispisi();
        s.PomjeriZa(-8);
        s.Ispisi();
        s.Postavi(12,3,4);
        s.Ispisi();

    }
}
