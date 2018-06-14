import modelo.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CementerioTest {

    @Test
    public void testCrearCementerioGeneraCementerioVacio() {

        Cementerio cementerio = new Cementerio();
        assertTrue(cementerio.estaVacio());

    }

    @Test
    public void testCrearCementerioGeneraCementerioConCantidadDeCartas0() {

        Cementerio cementerio = new Cementerio();
        assertEquals(cementerio.cantidadCartas(),0);

    }

    @Test
    public void testCementerioNoEstaVacioSiSeDestruyeUnaCarta() {

        Carta carta = new Monstruo(0,0);
        Cementerio cementerio = new Cementerio();

        cementerio.enviarCarta(carta);
        // carta.destruir;  ¿?

        assertFalse(cementerio.estaVacio());

    }

    @Test
    public void testEnviarUnaCartaAlCementerioAumentaCantidadDeCartasDelCementerioEn1() {

        // A correjir: ¿Se debe buscar la carta monstruo por nombre?
        Carta carta = new Monstruo(0,0);

        Cementerio cementerio = new Cementerio();

        cementerio.enviarCarta(carta);

        assertEquals(1, cementerio.cantidadCartas());

    }

}
