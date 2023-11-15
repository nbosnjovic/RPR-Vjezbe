package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TestZaImenik {

    private static Imenik imenik=new Imenik();


    @BeforeAll
    public static void setup(){
        imenik.dodaj("Naida", new FiksniBroj(Grad.SARAJEVO, "218-432"));
        imenik.dodaj("Nejra", new FiksniBroj(Grad.SARAJEVO, "258-892"));
        imenik.dodaj("Boris", new MobilniBroj(62, "234-222"));
        imenik.dodaj("Azra", new MobilniBroj(61, "258-892"));
        imenik.dodaj("Samra", new MedunarodniBroj("+44", "2587889892"));
        imenik.dodaj("Sajra", new MedunarodniBroj("+43", "2589644892"));
    }

    @Test
    public void dajBrojFound(){
        String broj=imenik.dajBroj("Naida");
        assertEquals(broj, "033/218-432");
    }

    @Test
    public void dajBrojNotFound(){
        String broj=imenik.dajBroj("Mirza");
        assertNull(broj);
    }

    @Test
    public void dodajTestPositives(){
        TelefonskiBroj broj=new MobilniBroj(62, "453-777");
        imenik.dodaj("Hana", broj);

        String brojString=imenik.dajBroj("Hana");
        assertEquals(brojString, "062/453-777");

    }

    @Test
    public void dodajFiksniException(){
        assertThrows(MojException.class, ()->{
                new FiksniBroj(null, "222-222");
        });
    }
    
}
