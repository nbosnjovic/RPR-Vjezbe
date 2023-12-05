package ba.unsa.etf.rpr;

import java.util.List;

public class KolekcijaImenaIPrezimena {
    private List<String> imena;
    private List<String> prezimena;

    public KolekcijaImenaIPrezimena(List<String> i, List<String> p){
        this.imena=i;
        this.prezimena=p;
    }
    public int getIndexNajduzegPara(){
        String najduze="";
        int index=-1;
        for (int i=0; i<imena.size(); i++){
            if (najduze.length()> (imena.get(i)+ prezimena.get(i)).length()){
                najduze=imena.get(i)+ prezimena.get(i);
                index=i;
            }
        }
        return index;
    }
    public String getImeIPrezime(int i){
        return imena.get(i)+ prezimena.get(i);
    }
}