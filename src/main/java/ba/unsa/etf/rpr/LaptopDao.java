package ba.unsa.etf.rpr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface LaptopDao {

    void dodajLaptopUListu(Laptop laptop);
    void dodajLaptopUFajl(Laptop laptop) throws IOException;
    ArrayList<Laptop> getLaptop(String procesor);
    void napuniListu (ArrayList<Laptop> laptopi);

    ArrayList<Laptop> vratiListuIzDatoteke() throws IOException, ClassNotFoundException;
}