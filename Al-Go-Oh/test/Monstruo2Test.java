import modelo.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import modelo.Monstruos.AgresorOscuro;
import modelo.Monstruos.AgujaAsesina;
import modelo.Monstruos.AsechadorDelCraneo;
import org.junit.Test;


public class Monstruo2Test {


    // Ataques entre Monstruos sin invocar
    @Test
    public void testAtacarMounstroEnPocisionAtaqueConIgualesPuntosDestruyeAmbosMounstros() {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        AgujaAsesina monstruoAtacante = new AgujaAsesina(new EstadoAtaque()); //ATK = 1200 DEF = 1000
        AgujaAsesina monstruoDefensor = new AgujaAsesina(new EstadoAtaque()); //ATK = 1200 DEF = 1000

        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(monstruoDefensor);

        assertEquals(1, atacante.getCantidadDestruida());
        assertEquals(1, defensor.getCantidadDestruida());
    }

    @Test
    public void testAtacarMounstroEnPocisionAtaqueConPuntosAtacanteMayorDestruyeDefensorYRestaDiferenciaEnPuntosJugadorQueDefiende() {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        AgujaAsesina monstruoAtacante = new AgujaAsesina(new EstadoAtaque()); //ATK = 1200 DEF = 1000
        AsechadorDelCraneo monstruoDefensor = new AsechadorDelCraneo(new EstadoAtaque()); //ATK = 900 DEF = 800
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(monstruoDefensor);

        assertEquals(0, atacante.getCantidadDestruida());
        assertEquals(1, defensor.getCantidadDestruida());
        assertEquals(atacante.getPuntosDeVida(), 8000);
        assertEquals(defensor.getPuntosDeVida(), 7700);
    }

    @Test
    public void testAtacarMounstroEnPocisionAtaqueConPuntosAtacanteMenorDestruyeAtacanteYRestaDiferenciaEnPuntosJugadorQueAtaca()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        AsechadorDelCraneo monstruoAtacante = new AsechadorDelCraneo(new EstadoAtaque()); //ATK = 900 DEF = 800
        AgujaAsesina monstruoDefensor = new AgujaAsesina(new EstadoAtaque()); //ATK = 1200 DEF = 1000
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(monstruoDefensor);

        assertEquals(1, atacante.getCantidadDestruida());
        assertEquals(0, defensor.getCantidadDestruida());
        assertEquals(atacante.getPuntosDeVida(), 7700);
        assertEquals(defensor.getPuntosDeVida(), 8000);
    }

    @Test
    public void testAtacarMounstroEnPocisionDefensaConMenoresPuntosDestruyeMounstroDefensor()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        AgujaAsesina monstruoAtacante = new AgujaAsesina(new EstadoAtaque()); //ATK = 1200 DEF = 1000
        AsechadorDelCraneo monstruoDefensor = new AsechadorDelCraneo(new EstadoDefensaBocaAbajo()); //ATK = 900 DEF = 800
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(monstruoDefensor);

        assertEquals(0, atacante.getCantidadDestruida());
        assertEquals(1, defensor.getCantidadDestruida());
        assertEquals(atacante.getPuntosDeVida(), 8000);
        assertEquals(defensor.getPuntosDeVida(), 8000);
    }

    @Test
    public void testAtacarMounstroEnPocisionDefensaConIgualesPuntosNoProduceCambios()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        AgresorOscuro monstruoAtacante = new AgresorOscuro(new EstadoAtaque()); //ATK = 1200 DEF = 1200
        AgresorOscuro monstruoDefensor = new AgresorOscuro(new EstadoDefensaBocaAbajo()); //ATK = 1200 DEF = 1200
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(monstruoDefensor);

        assertEquals(0, atacante.getCantidadDestruida());
        assertEquals(0, defensor.getCantidadDestruida());
        assertEquals(atacante.getPuntosDeVida(), 8000);
        assertEquals(defensor.getPuntosDeVida(), 8000);
    }

    @Test
    public void testAtacarMounstroEnPocisionDefensaConMayoresPuntosNoDestruyeMounstroAtacanteYRestaDiferenciaEnPuntosAlJugadorQueAtaca()
    {
        Jugador atacante = new Jugador(); //Juego.getJuego().getJugadorActivo();
        Jugador defensor = new Jugador(); //Juego.getJuego().getJugadorOponente();
        AsechadorDelCraneo monstruoAtacante = new AsechadorDelCraneo(new EstadoAtaque()); //ATK = 900 DEF = 800
        AgujaAsesina monstruoDefensor = new AgujaAsesina(new EstadoDefensaBocaAbajo()); //ATK = 1200 DEF = 1000
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(monstruoDefensor);

        assertEquals(0, atacante.getCantidadDestruida());
        assertEquals(0, defensor.getCantidadDestruida());
        assertEquals(atacante.getPuntosDeVida(), 7900);
        assertEquals(defensor.getPuntosDeVida(), 8000);

    }






}