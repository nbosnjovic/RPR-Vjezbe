package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;


public class Main {

    public static String ispisiGradove(){
        GeografijaDAO dao = GeografijaDAO.getDao();
        ArrayList<Grad> gradovi = dao.gradovi();
        String rez = "";
        if(gradovi!=null) {
            for (Grad g : gradovi) {
                rez += g.getNaziv() + " (" + g.getDrzava() + ")" + " - " + g.getBrojStanovnika() + "\n";
            }
        }
        return rez;
    }

    public static void glavniGrad(){
        GeografijaDAO dao = GeografijaDAO.getDao();
        Scanner ulaz = new Scanner(System.in);
        System.out.print("Unesite naziv države : ");
        String nazivDrzave = ulaz.nextLine();
        Drzava drzava = dao.nadjiDrzavu(nazivDrzave);
        if(drzava != null) {
            System.out.printf("\nGlavni grad države " + drzava.getNaziv() + " je " + drzava.getGlavniGrad().getNaziv());
            return;
        }
        System.out.print("\nNepostojeća država");
    }


    public static void main(String[] args) throws SQLException {
        System.out.println("Gradovi su:\n" + ispisiGradove());
        glavniGrad();
    }
}
