import modelo.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ManoTest {

    @Test
    public void testCrearManoGeneraManoVacia(){
        Mano mano = new Mano();

        assertTrue(mano.estaVacia());

    }

    @Test
    public void testManoExtrae1CartaDelMazoYAumentaSuCantidadEn1(){
        Mano mano = new Mano();
        Mazo mazo = new Mazo();

        Carta carta = mazo.extaerCarta();
        mano.agregarCarta(carta);

        assertEquals(mano.cantidadCartas(),1);

    }






}
