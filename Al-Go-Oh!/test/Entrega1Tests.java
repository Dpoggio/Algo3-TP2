import modelo.*;
import org.junit.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Entrega1Tests {




    @Test
    public void testEnviarUnaCartaAlCementerioAumentaCantidadDeCartasDelCementerioEn1() {

        // A correjir: ¿Se debe buscar la carta monstruo por nombre?
        Carta carta = new Monstruo(0,0);

        Cementerio cementerio = new Cementerio();

        cementerio.enviarCarta(carta);

        assertEquals(1, cementerio.cantidadCartas());

    }


 }
