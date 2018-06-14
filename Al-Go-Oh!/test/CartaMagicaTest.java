import modelo.*;
import modelo.CartasMagicas.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CartaMagicaTest {

    @Test
    public void testInvocarAgujeroNegroEnTableroDestruyeTodosLosMonstruosDelTableroDelInvocador() {

        AgujeroNegro agujeroNegro = new AgujeroNegro(Colocacion.BOCAARRIBA);

        Jugador jugador = new Jugador();

        Monstruo monstruo = new Monstruo(0,0,Posicion.DEFENSA,4);

        jugador.colocarEnAtaque(monstruo);
        jugador.colocarCarta(agujeroNegro);

        assertEquals(0,jugador.obtenerCampo().cantidadCartasZonaMonstruos());
        assertEquals(0,jugador.obtenerCampo().cantidadCartasZonaMagicas());

    }

}
