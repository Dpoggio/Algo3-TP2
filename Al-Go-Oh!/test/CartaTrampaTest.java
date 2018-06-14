import modelo.Carta;
import modelo.CartaTrampa;
import modelo.Juego;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CartaTrampaTest {

    @Test
    public void colocarCartaTrampaColocaLaCartaBocaAbajo(){
        CartaTrampa carta = new CartaTrampa();
        Juego.getJuego().getJugadorActivo().colocarCarta(carta);
        ArrayList<Carta> cartas = Juego.getJuego().getJugadorActivo().obtenerCampo().obtenerHechizosEnCampo();
        assertTrue(cartas.contains(carta));
        for (Carta cartaActual:cartas) {
            if (cartaActual == carta) {
                assertTrue(cartaActual.estaBocaAbajo());
                return;
            }
        }
        assertEquals(200,0);
    }
}
