package ba.unsa.etf.rpr;
public class SinFakt {
    public static double Sinus(double broj){
        return Math.sin(broj);
    }

    public static long Faktorijel(double broj){
        if (broj == 0) {
            return 1;
        }
        long faktorijel = 1;
        for (int i = 1; i <= broj; i++) {
            faktorijel *= i;
        }
        return faktorijel;
    }
}
