package ba.unsa.etf.rpr;

import java.util.Scanner;

public class Main {

    public static int SumaCifara(int n){
        int suma=0;
        while(n!=0){
            suma+=n%10;
            n/=10;
        }
        return suma;
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Unesite broj n: ");
        int n=scanner.nextInt();

        System.out.println("Brojevi od 1 do "+n+" koji su djeljivi sa sumom svojih cifara: ");
        for(int i=1;i<=n;i++){
            int suma=SumaCifara(n);

            if(i%suma==0){
                System.out.print(i+" ");
            }
        }

    }
}

