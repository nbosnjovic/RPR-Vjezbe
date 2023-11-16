package ba.unsa.etf.rpr;

import java.io.*;
import java.util.ArrayList;

    public class LaptopDaoXMLFile implements LaptopDao{

        private File file;
        private ArrayList<Laptop> laptopi;

        public LaptopDaoXMLFile(){
            file = new File("/Datoteke/java.xml");
            laptopi = new ArrayList<>();
        }

        @Override
        public void dodajLaptopUListu(Laptop laptop) {
            laptopi.add(laptop);
        }

        @Override
        public void dodajLaptopUFajl(Laptop laptop) throws IOException {
            laptopi.add(laptop);
            FileOutputStream fos = new FileOutputStream(this.file);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(laptopi);
            os.close();
            fos.close();
        }

        @Override
        public ArrayList<Laptop> getLaptop(String procesor) {
            ArrayList temp = new ArrayList<Laptop>();
            for( Laptop i: laptopi){
                if(i.getProcesor().equals(procesor)) temp.add(i);
            }
            return temp;
        }

        @Override
        public void napuniListu(ArrayList<Laptop> laptopi) {
            for(Laptop i : laptopi) this.laptopi.add(i);
        }

        @Override
        public ArrayList<Laptop> vratiListuIzDatoteke() throws IOException {
            ArrayList<Laptop> laptops = new ArrayList<>();
            FileInputStream fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                laptops = (ArrayList<Laptop>) ois.readObject();
            }catch (ClassNotFoundException e){
                throw new RuntimeException(e);
            }
            return laptops;
        }
    }
