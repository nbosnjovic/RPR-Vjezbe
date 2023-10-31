package ba.unsa.etf.rpr;

public class Main {
    public static void main(String[] args) {

                if (args.length != 1) {
                    System.out.println("Unesite tačno jedan broj.");
                    return;
                }

                try {
                    double broj = Double.parseDouble(args[0]);

                    double sinus = SinFakt.Sinus(broj);
                    long faktorijel = SinFakt.Faktorijel((int) broj);

                    System.out.println("Sinus broja " + broj + " je: " + sinus);
                    System.out.println("Faktorijel broja " + broj + " je: " + faktorijel);
                } catch (NumberFormatException e) {
                    System.out.println("Niste unijeli ispravan broj.");
                }
            }
        }

