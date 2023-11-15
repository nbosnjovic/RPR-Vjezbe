package ba.unsa.etf.rpr;

import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Imenik imenik = new Imenik();

    public static void main(String[] args) {
        popuniPodatke();
        while (true) {
            System.out.println("Unesite komandu[dodaj, dajBroj, dajIme, naSlovo, izGrada, izGradaBrojevi, imenik, izlaz]");
            String command = scanner.nextLine();
            switch (command) {
                case "dodaj":
                    dodajBroj();
                    break;
                case "dajBroj":
                    dajBroj();
                    break;
                case "dajIme":
                    dajIme();
                    break;
                case "naSlovo":
                    naSlovo();
                    break;
                case "izGrada":
                    izGrada();
                    break;
                case "izGradaBrojevi":
                    izGradaBrojevi();
                    break;
                case "imenik":
                    ispisiImenik();
                    break;
                case "izlaz":
                    izlaz();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pogresna komanda.");
            }
        }
    }

    private static void izGradaBrojevi() {
        System.out.println("Unesite ime grada: ");
        String grad = scanner.nextLine();
        try {
            Grad g = Grad.valueOf(grad);
            Set<TelefonskiBroj> response = imenik.izGradaBrojevi(g);
            for (TelefonskiBroj b : response) {
                System.out.println(b.ispisi());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Pogresan grad");
        }
    }

    private static void izGrada() {
        System.out.println("Unesite ime grada: ");
        String grad = scanner.nextLine();
        try {
            Grad g = Grad.valueOf(grad);
            Set<String> response = imenik.izGrada(g);
            System.out.println(response);
        } catch (IllegalArgumentException e) {
            System.out.println("Pogresan grad");
        }
    }

    private static void naSlovo() {
        System.out.println("Unesite prvo slovo imena: ");
        String c = scanner.nextLine();
        String response = imenik.NaSlovo(c.charAt(0));
        System.out.println(response);
    }

    private static void dajIme() {
        TelefonskiBroj br = unesiBrojTelefona();
        String ime = imenik.dajIme(br);
        if (ime == null) {
            System.out.println("Ne postoji u imeniku");
        } else {
            System.out.println("Vlasnik broja " + br.ispisi() + " je " + ime);
        }
    }

    private static TelefonskiBroj unesiBrojTelefona() {
        System.out.println("Unesite tip broja [fiksni, mobilni, medunarodni]: ");
        String type = scanner.nextLine();
        switch (type) {
            case "fiksni":
                System.out.println("Unesite pozivni: ");
                String pozivni = scanner.nextLine();
                System.out.println("Unesite broj: ");
                String broj = scanner.nextLine();
                return new FiksniBroj(Grad.izPozivnog(pozivni), broj);

            case "mobilni":
                System.out.println("Unesite pozivni: ");
                int mreza = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                System.out.println("Unesite broj: ");
                String mobilniBroj = scanner.nextLine();
                return new MobilniBroj(mreza, mobilniBroj);

            case "medunarodni":
                System.out.println("Unesite kod drzave [+387]: ");
                String kod = scanner.nextLine();
                System.out.println("Unesite broj: ");
                String medBroj = scanner.nextLine();
                return new MedunarodniBroj(kod, medBroj);
        }
        return null;
    }

    private static void dodajBroj() {
        System.out.println("Unesite ime: ");
        String name = scanner.nextLine();
        TelefonskiBroj br = unesiBrojTelefona();
        imenik.dodaj(name, br);
    }

    private static void ispisiImenik() {
        System.out.println(imenik.toString());
    }

    private static void dajBroj() {
        System.out.println("Unesite ime: ");
        String ime = scanner.nextLine();
        String rezultat = imenik.dajBroj(ime);
        System.out.println(rezultat == null ? "Nema u imeniku" : rezultat);
    }

    private static void izlaz() {
        System.out.println("Izlaz iz programa.");
    }

    private static void popuniPodatke() {
    }
}
