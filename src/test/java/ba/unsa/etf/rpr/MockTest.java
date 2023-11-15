package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class MockTest {
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
    public void testMockExternal(){
        Imenik i=Mockito.mock(Imenik.class);
        Mockito.when(i.dajBroj("Naida")).thenReturn("nije nadjeno");

        String test=i.dajBroj("Naida");
        assertEquals(test, "nije nadjeno");
    }

    @Test

    public void testMockInternal(){
        Map<String, TelefonskiBroj>mapa=Mockito.mock(Map.class);
        Mockito.when(mapa.get("Naida")).thenReturn(new FiksniBroj(Grad.SARAJEVO, "345-678"));
        imenik.setBrojevi(mapa);

        String broj=imenik.dajBroj("Naida");
        assertNotEquals(broj, "345-678");
        assertEquals(broj, "345-678");
    }
}
