import modelo.*;
import modelo.CartasMagicas.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void testInvocarCartaMagicaAumentaEn1LaCantidadDeCartasEnZonaMagicas() {

        AgujeroNegro agujeroNegro = new AgujeroNegro(Colocacion.BOCAARRIBA);

        Jugador jugador = new Jugador();

        jugador.colocarCarta(agujeroNegro);

        assertEquals(1,jugador.obtenerCampo().cantidadCartasZonaMagicas());

    }

    @Test
    public void testInvocarCartaMonstruoAumentaEn1LaCantidadDeCartasEnZonaMonstruos() {

        Jugador jugador = new Jugador();

        Monstruo monstruo = new Monstruo(0,0,Posicion.DEFENSA,4);
        jugador.colocarEnAtaque(monstruo);
        assertEquals(1,jugador.obtenerCampo().cantidadCartasZonaMonstruos());

    }

}
