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
    public void testIniciarManoGeneraManoCon5Cartas(){
        Mano mano = new Mano();
        Mazo mazo = new Mazo();

        mano.iniciarMano(mazo);

        assertEquals(mano.cantidadCartas(),5);

    }

    @Test
    public void testManoExtrae1CartaDelMazoYAumentaSuCantidadEn1(){
        Mano mano = new Mano();
        Mazo mazo = new Mazo();

        mano.extraerCarta(mazo);

        assertEquals(mano.cantidadCartas(),1);

    }






}
