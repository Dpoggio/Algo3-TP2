import junit.framework.TestCase;
import modelo.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import modelo.CartasMagicas.AgujeroNegro;
import modelo.CartasMagicas.CilindroMagico;
import org.junit.Test;

public class Entrega1Tests {



    @Test
    public void test01PrimerEntrega(){
        /*Colocar una carta de monstruo en posición de ataque.*/
        // suponiendo que existe la clase posición
       // Posicion nuevaPosicion = new Posicion();
       // Monstruo monstruo = new Monstruo("test",100,100,Posicion.ATAQUE,4);

        Monstruo monstruo = new Monstruo("test",100,100,new Posicion(),4);
    //toda la info de los puntos de ataque y de defensa y de estrellas puede ir en un factory de monstruos.
        assertEquals(Posicion.ATAQUE,monstruo.getPosicion());
    }

    @Test
    public void test02PrimerEntrega() {
        /*Colocar un monstruo en posición de defensa*/

        Monstruo monstruo = new Monstruo("test",100,100,Posicion.DEFENSA,4);

        assertEquals(Posicion.DEFENSA,monstruo.getPosicion());
    }

    @Test
    public void test03PrimerEntrega() {
        /*Colocar una carta mágica en el campo boca abajo (no activa ningún efecto)*/

        AgujeroNegro agujeroNegro = new AgujeroNegro();
        Jugador jugador = new Jugador();
        jugador.colocarCarta(agujeroNegro);

        assertTrue(jugador.obtenerCampo().getZonaHechizos().obtenerCartaPosicion(Casillero.UNO).estaBocaAbajo());
    }


    @Test
    public void test04PrimerEntrega() {
        /*Colocar una carta trampa en el campo boca abajo*/

        CartaTrampa carta = new CilindroMagico();
        Jugador jugador = new Jugador();
        jugador.colocarCarta(carta);

        TestCase.assertTrue(jugador.obtenerCampo().getZonaHechizos().obtenerCartaPosicion(Casillero.UNO).estaBocaAbajo());
    }


    @Test
    public void test05PrimerEntrega() {
        /*Mandar una carta al cementerio (sinónimo de destruir) y verificar que estén ahí.*/

        Carta carta = new Monstruo(0,0);

        Cementerio cementerio = new Cementerio();

        cementerio.enviarCarta(carta);

        assertEquals(1, cementerio.cantidadCartas());
    }

    @Test
    public void test06PrimerEntrega() {
        /*Colocar una carta de monstruo en posición de ataque, el oponente coloca otra carta de monstruo en posición de ataque (con mayor ataque). Atacar al primer monstruo y verificar que este se destruyó, y sufro daño a los puntos de vida igual a la diferencia de los puntos de ataque de los monstruos*/

        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test",1000,100, Posicion.ATAQUE,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnAtaque(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(1,atacante.cantidadCartasCementerio());
        assertEquals(0,defensor.cantidadCartasCementerio());
        assertEquals(atacante.getPuntosDeVida(), 7500);
        assertEquals(defensor.getPuntosDeVida(), 8000);
    }

    @Test
    public void test07PrimerEntrega() {
        /*Colocar una carta de monstruo en posición de ataque, el oponente coloca otra carta de monstruo en posición de ataque (con menor ataque), atacar al primer monstruo, verificar que el monstruo atacante es destruido y el atacante recibe daño a los puntos de vida igual a la diferencia de ataques.*/

        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test",1000, 2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test",500, 100, Posicion.ATAQUE,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnAtaque(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(0,atacante.cantidadCartasCementerio());
        assertEquals(1,defensor.cantidadCartasCementerio());
        assertEquals(atacante.getPuntosDeVida(), 8000);
        assertEquals(defensor.getPuntosDeVida(), 7500);
    }

    @Test
    public void test08PrimerEntrega() {
        /*Colocar una carta de monstruo en posición de ataque, el oponente coloca otra carta de monstruo en posición de ataque (con igual ataque), atacar al primer monstruo, verificar que ambos monstruos son destruidos y nadie recibe daño a los puntos de vida.*/

        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test",500,100, Posicion.ATAQUE,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnAtaque(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(1,atacante.cantidadCartasCementerio());
        assertEquals(1,defensor.cantidadCartasCementerio());
    }

    @Test
    public void test09PrimerEntrega() {
        /*Colocar una carta de monstruo en posición de defensa, el oponente coloca otra carta de monstruo en posición de ataque (con mayor ataque que la defensa del primer monstruo), atacar al primer monstruo y verificar que este se destruyó y no sufrió ningún daño vital.*/

        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test",1000,100, Posicion.DEFENSA,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnDefensa(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(1,defensor.cantidadCartasCementerio());
        assertEquals(8000, defensor.getPuntosDeVida());
        assertEquals(8000,atacante.getPuntosDeVida());
    }

    @Test
    public void test10PrimerEntrega() {
        /*Colocar una carta de monstruo en posición de defensa, el oponente coloca otra carta de monstruo en posición de ataque (con menor ataque que la defensa del primer monstruo), atacar al primer monstruo y verificar que este no se destruyó y no sufrió ningún daño vital.*/

        Jugador atacante = new Jugador(); //Juego.getJuego().getJugadorActivo();
        Jugador defensor = new Jugador(); //Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new Monstruo("test",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test",1000,1000, Posicion.DEFENSA,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnDefensa(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(0, atacante.cantidadCartasCementerio());
        assertEquals(0, defensor.cantidadCartasCementerio());
        assertEquals(7500, atacante.getPuntosDeVida());
        assertEquals(8000, defensor.getPuntosDeVida());

    }

    @Test
    public void test11PrimerEntrega() {
        /*Colocar monstruos en ambos lados del campo. Colocar “Agujero negro” boca arriba (es decir, se activa el efecto). Verificar que se destruyeron todos los monstruos de ambos lados del campo, y que nadie recibió daño alguno.*/

        Juego.reiniciarJuego();
        AgujeroNegro agujeroNegro = new AgujeroNegro(Colocacion.BOCAARRIBA);

        Jugador jugadorActivo = Juego.getJuego().getJugadorActivo();
        Jugador jugadorOponente = Juego.getJuego().getJugadorOponente();

        Monstruo monstruoJugadorActivo = new Monstruo("test",0,0,Posicion.DEFENSA,4);
        Monstruo monstruoJugadorOponente = new Monstruo("test",0,0,Posicion.DEFENSA,4);

        jugadorActivo.colocarEnAtaque(monstruoJugadorActivo);
        jugadorOponente.colocarEnAtaque(monstruoJugadorOponente);

        jugadorActivo.colocarCarta(agujeroNegro);

        assertEquals(0,jugadorActivo.obtenerCampo().cantidadCartasZonaMonstruos());
        assertEquals(0,jugadorOponente.obtenerCampo().cantidadCartasZonaMonstruos());
        assertEquals(0,jugadorActivo.obtenerCampo().cantidadCartasZonaMagicas());
    }

    @Test
    public void test12PrimerEntrega() {
        /*Se coloca un monstruo en el campo, se quiere colocar un monstruo de 5 o 6 estrellas que requiere sacrificio. se verifica que se convocó al monstruo y se destruyó el primero.*/

        Monstruo aSacrificar= new Monstruo("test",0,0,Posicion.ATAQUE,3);
        Monstruo sacrificador = new Monstruo("test",0,0,Posicion.ATAQUE,5);
        Jugador jugador = new Jugador();

        jugador.colocarEnAtaque(aSacrificar);
        jugador.colocarEnAtaque(sacrificador);
        assertEquals(1,jugador.cantidadCartasCementerio());
    }

    @Test
    public void test13PrimerEntrega() {
        /*Se colocan 2 monstruos en el campo, se quiere colocar un monstruo de 7 o más estrellas que requiere 2 sacrificios. se verifica que se convocó al monstruo y se destruyeron los demás.*/

        Jugador jugador = new Jugador();
        Monstruo aSacrificar1= new Monstruo("test",0,0,Posicion.ATAQUE,3);
        Monstruo aSacrificar2= new Monstruo("test",0,0,Posicion.ATAQUE,3);
        Monstruo sacrificador = new Monstruo("test",0,0,Posicion.ATAQUE,7);
        jugador.colocarEnAtaque(aSacrificar1);
        jugador.colocarEnAtaque(aSacrificar2);
        jugador.colocarEnAtaque(sacrificador);
        assertEquals(2,jugador.cantidadCartasCementerio());
    }




 }
