import modelo.Juego;
import modelo.Jugador;
import modelo.Monstruo;
import modelo.Posicion;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonstruoTest {

    @Test
    public void testAtacarMounstroEnPocisionAtaqueConIgualesPuntosDestruyeAmbosMounstros()
    {
        Jugador atacante = Juego.getJuego().getJugadorActivo();
        Jugador defensor = Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new Monstruo(500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo(500,100, Posicion.ATAQUE,1);

        monstruoAtacante.atacarMounstro(monstruoDefensor);

        //assertTrue(atacante.cantidadCartasCementerio() == 1 && defensor.cantidadCartasCementerio() == 1);
        assertEquals(1,atacante.cantidadCartasCementerio());
        assertEquals(1,defensor.cantidadCartasCementerio());
    }

    @Test
    public void testAtacarMounstroEnPocisionAtaqueConPuntosAtacanteMayorDestruyeDefensorYRestaDiferenciaEnPuntosJugadorQueDefiende() {
        Juego.reiniciarJuego();
        Jugador atacante = Juego.getJuego().getJugadorActivo();
        Jugador defensor = Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new Monstruo(1000, 2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo(500, 100, Posicion.ATAQUE,1);

        monstruoAtacante.atacarMounstro(monstruoDefensor);

        //assertTrue(atacante.cantidadCartasCementerio() == 0 && defensor.cantidadCartasCementerio() == 1);
        assertEquals(0,atacante.cantidadCartasCementerio());
        assertEquals(1,defensor.cantidadCartasCementerio());
        assertEquals(atacante.getPuntosDeVida(), 8000);
        assertEquals(defensor.getPuntosDeVida(), 7500);
    }

    @Test
    public void testAtacarMounstroEnPocisionAtaqueConPuntosAtacanteMenorDestruyeAtacanteYRestaDiferenciaEnPuntosJugadorQueAtaca()
    {
        Juego.reiniciarJuego();
        Jugador atacante = Juego.getJuego().getJugadorActivo();
        Jugador defensor = Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new Monstruo(500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo(1000,100, Posicion.ATAQUE,1);

        monstruoAtacante.atacarMounstro(monstruoDefensor);

        //assertTrue(atacante.cantidadCartasCementerio() == 0 && defensor.cantidadCartasCementerio() == 1);
        assertEquals(1,atacante.cantidadCartasCementerio());
        assertEquals(0,defensor.cantidadCartasCementerio());
        assertEquals(atacante.getPuntosDeVida(), 7500);
        assertEquals(defensor.getPuntosDeVida(), 8000);
    }

    @Test
    public void testAtacarMounstroEnPocisionDefensaConMenoresPuntosDestruyeMounstroDefensor()
    {
        Juego.reiniciarJuego();
        Jugador atacante = Juego.getJuego().getJugadorActivo();
        Jugador defensor = Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new Monstruo(500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo(1000,100, Posicion.DEFENSA,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnDefensa(monstruoDefensor);
        monstruoAtacante.atacarMounstro(monstruoDefensor);

        assertEquals(1,defensor.cantidadCartasCementerio());
    }

    @Test
    public void testAtacarMounstroEnPocisionDefensaConIgualesPuntosNoProduceCambios()
    {
        Juego.reiniciarJuego();
        Jugador atacante = Juego.getJuego().getJugadorActivo();
        Jugador defensor = Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new Monstruo(500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo(1000,500, Posicion.DEFENSA,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnDefensa(monstruoDefensor);
        monstruoAtacante.atacarMounstro(monstruoDefensor);

        //assertTrue(defensor.cantidadCartasCementerio() == 0 && atacante.cantidadCartasCementerio() == 0);
        assertEquals(0,defensor.cantidadCartasCementerio());
        assertEquals(0,atacante.cantidadCartasCementerio());
    }

    @Test
    public void testAtacarMounstroEnPocisionDefensaConMayoresPuntosNoDestruyeMounstroAtacanteYRestaDiferenciaEnPuntosAlJugadorQueAtaca()
    {
        Juego.reiniciarJuego();
        Jugador atacante = Juego.getJuego().getJugadorActivo();
        Jugador defensor = Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new Monstruo(500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo(1000,1000, Posicion.DEFENSA,1);

        monstruoAtacante.atacarMounstro(monstruoDefensor);

        assertEquals(0, atacante.cantidadCartasCementerio());
        assertEquals(0, defensor.cantidadCartasCementerio());
        assertEquals(atacante.getPuntosDeVida(), 7500);
        assertEquals(defensor.getPuntosDeVida(), 8000);

    }

    @Test
    public void testMonstruoConMenosDeCuatroEstrellasNoRequiereSacrificio(){
        Monstruo monstruo = new Monstruo(0,0,Posicion.ATAQUE,3);
        assertFalse(monstruo.requiereSacrificio());
    }

    @Test
    public void testMonstruoConMasDeCuatroEstrellasRequiereSacrificio(){
        Monstruo monstruo = new Monstruo(0,0,Posicion.ATAQUE,6);
        assertTrue(monstruo.requiereSacrificio());
    }

    @Test
    public void testMonstruoConMasDeCuatroEstrellasRequiereSacrificarUnMonstruo(){
        Monstruo monstruo = new Monstruo(0,0,Posicion.ATAQUE,5);
        assertEquals(monstruo.cantidadASacrificar(),1);
    }

    @Test
    public void testMonstruoConMasDeSeisEstrellasRequiereSacrificarDosMonstruos(){
        Monstruo monstruo = new Monstruo(0,0,Posicion.ATAQUE,7);
        assertEquals(monstruo.cantidadASacrificar(),2);
    }

    @Test
    public void testColocarMonstruoCon5EstrellasSacrificaUnMonstruo(){
        Monstruo aSacrificar= new Monstruo(0,0,Posicion.ATAQUE,3);
        Monstruo sacrificador = new Monstruo(0,0,Posicion.ATAQUE,5);
        Juego.getJuego().getJugadorActivo().colocarEnAtaque(aSacrificar);
        Juego.getJuego().getJugadorActivo().colocarEnAtaque(sacrificador);
        assertEquals(1,Juego.getJuego().getJugadorActivo().cantidadCartasCementerio());
    }

    @Test
    public void testColocarMonstruoCon7EstrellasSacrificaDosMonstruos(){
        Monstruo aSacrificar1= new Monstruo(0,0,Posicion.ATAQUE,3);
        Monstruo aSacrificar2= new Monstruo(0,0,Posicion.ATAQUE,3);
        Monstruo sacrificador = new Monstruo(0,0,Posicion.ATAQUE,7);
        Juego.getJuego().getJugadorActivo().colocarEnAtaque(aSacrificar1);
        Juego.getJuego().getJugadorActivo().colocarEnAtaque(aSacrificar2);
        Juego.getJuego().getJugadorActivo().colocarEnAtaque(sacrificador);
        assertEquals(2,Juego.getJuego().getJugadorActivo().cantidadCartasCementerio());
    }


}
