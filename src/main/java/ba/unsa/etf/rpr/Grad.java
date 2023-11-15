package ba.unsa.etf.rpr;

public enum Grad {
    TRAVNIK ("030"),
    SARAJEVO ("033"),
    ZENICA ("032"),
    LIVNO ("034"),
    TUZLA ("035"),
    MOSTAR ("036"),
    BIHAC ("037"),
    GORAZDE ("038"),
    BRCKO ("049");

    private String pozivniBroj;
    Grad(String pozivniBroj){
        this.pozivniBroj=pozivniBroj;
    }
    public static Grad izPozivnog(String pozivni){
        for(Grad g: Grad.values()){
            if(g.getPozivniBroj().equals(pozivni)){
                return g;
            }
        }
        return null;
    }
    public String getPozivniBroj(){
        return pozivniBroj;
    }
}
