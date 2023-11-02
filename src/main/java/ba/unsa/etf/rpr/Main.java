
package ba.unsa.etf.rpr;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Double> brojevi = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Unesite brojeve (ili \"stop\" za prekid unosa):");

        while (true) {
            String unos = scanner.nextLine();

            if (unos.equalsIgnoreCase("stop")) {
                break;
            }

            try {
                double broj = Double.parseDouble(unos);
                brojevi.add(broj);
            } catch (NumberFormatException e) {
                System.out.println("Nevažeći unos. Molimo unesite broj ili \"stop\".");
            }
        }

        if (brojevi.isEmpty()) {
            System.out.println("Nema unesenih brojeva.");
        } else {
            double min = brojevi.get(0);
            double max = brojevi.get(0);
            double suma = 0;

            for (double broj : brojevi) {
                if (broj < min) {
                    min = broj;
                }
                if (broj > max) {
                    max = broj;
                }
                suma += broj;
            }

            double mean = suma / brojevi.size();

            double sumaKv = 0;
            for (double broj : brojevi) {
                sumaKv += Math.pow(broj - mean, 2);
            }

            double standardnaDevijacija = Math.sqrt(sumaKv / brojevi.size());

            System.out.println("Minimum: " + min);
            System.out.println("Maksimum: " + max);
            System.out.println("Srednja vrijednost: " + mean);
            System.out.println("Standardna devijacija: " + standardnaDevijacija);
        }

        scanner.close();
    }
}
