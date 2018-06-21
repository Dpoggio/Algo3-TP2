import modelo.*;

import modelo.CartasMagicas.AgujeroNegro;
import org.junit.Test;

import java.io.IOException;

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
        Mazo mazo = null;
        try {
            mazo = new Mazo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mazo.agregarCarta(new AgujeroNegro());

        Carta carta = mazo.extaerCarta();
        mano.agregarCarta(carta);

        assertEquals(mano.cantidadCartas(),1);

    }






}