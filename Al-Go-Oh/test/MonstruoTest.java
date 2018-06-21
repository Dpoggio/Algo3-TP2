import modelo.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class MonstruoTest {

    @Test
    public void testColocarMonstruoEnPosicionDeAtaqueGeneraMonstruoEnPosicionDeAtaque(){
        Monstruo monstruo = new Monstruo("test",100,100,Posicion.ATAQUE,4);

        assertEquals(Posicion.ATAQUE,monstruo.getPosicion());
    }

    @Test
    public void testColocarMonstruoEnPosicionDeDefensaGeneraMonstruoEnPosicionDeDefensa(){
        Monstruo monstruo = new Monstruo("test",100,100,Posicion.DEFENSA,4);

        assertEquals(Posicion.DEFENSA,monstruo.getPosicion());
    }


    // Ataques entre Monstruos sin invocar
    @Test
    public void testAtacarMounstroEnPocisionAtaqueConIgualesPuntosDestruyeAmbosMounstros()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test1",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",500,100, Posicion.ATAQUE,1);
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(atacante, defensor, monstruoDefensor);

        assertEquals(1,atacante.cantidadCartasCementerio());
        assertEquals(1,defensor.cantidadCartasCementerio());
    }

    @Test
    public void testAtacarMounstroEnPocisionAtaqueConPuntosAtacanteMayorDestruyeDefensorYRestaDiferenciaEnPuntosJugadorQueDefiende()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test1",1000, 2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",500, 100, Posicion.ATAQUE,1);
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(atacante, defensor, monstruoDefensor);

        assertEquals(0,atacante.cantidadCartasCementerio());
        assertEquals(1,defensor.cantidadCartasCementerio());
        assertEquals(atacante.getPuntosDeVida(), 8000);
        assertEquals(defensor.getPuntosDeVida(), 7500);
    }

    @Test
    public void testAtacarMounstroEnPocisionAtaqueConPuntosAtacanteMenorDestruyeAtacanteYRestaDiferenciaEnPuntosJugadorQueAtaca()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test1",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",1000,100, Posicion.ATAQUE,1);
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(atacante, defensor, monstruoDefensor);

        assertEquals(1,atacante.cantidadCartasCementerio());
        assertEquals(0,defensor.cantidadCartasCementerio());
        assertEquals(atacante.getPuntosDeVida(), 7500);
        assertEquals(defensor.getPuntosDeVida(), 8000);
    }

    @Test
    public void testAtacarMounstroEnPocisionDefensaConMenoresPuntosDestruyeMounstroDefensor()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test1",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",1000,100, Posicion.DEFENSA,1);
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(atacante, defensor, monstruoDefensor);

        assertEquals(1,defensor.cantidadCartasCementerio());
    }

    @Test
    public void testAtacarMounstroEnPocisionDefensaConIgualesPuntosNoProduceCambios()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test1",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",1000,500, Posicion.DEFENSA,1);
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(atacante, defensor, monstruoDefensor);

        assertEquals(0,defensor.cantidadCartasCementerio());
        assertEquals(0,atacante.cantidadCartasCementerio());
    }

    @Test
    public void testAtacarMounstroEnPocisionDefensaConMayoresPuntosNoDestruyeMounstroAtacanteYRestaDiferenciaEnPuntosAlJugadorQueAtaca()
    {
        Jugador atacante = new Jugador(); //Juego.getJuego().getJugadorActivo();
        Jugador defensor = new Jugador(); //Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new Monstruo("test1",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",1000,1000, Posicion.DEFENSA,1);
        monstruoAtacante.asociarJugador(atacante);
        monstruoDefensor.asociarJugador(defensor);

        monstruoAtacante.atacarMonstruo(atacante, defensor, monstruoDefensor);

        assertEquals(0, atacante.cantidadCartasCementerio());
        assertEquals(0, defensor.cantidadCartasCementerio());
        assertEquals(7500, atacante.getPuntosDeVida());
        assertEquals(8000, defensor.getPuntosDeVida());

    }



    //Ataques invocando monstruos
    @Test
    public void testInvocarMonstruoEnPosicionDeAtaqueYAtacarMonstruoEnPosicionAtaqueConIgualesPuntosDestruyeAmbosMounstros()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test1",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",500,100, Posicion.ATAQUE,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnAtaque(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(1,atacante.cantidadCartasCementerio());
        assertEquals(1,defensor.cantidadCartasCementerio());
    }


    @Test
    public void testInvocarMonstruoEnPosicionDeAtaqueYAtacarMonstruoEnPocisionAtaqueConPuntosAtacanteMayorDestruyeDefensorYRestaDiferenciaEnPuntosJugadorQueDefiende()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test1",1000, 2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",500, 100, Posicion.ATAQUE,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnAtaque(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(0,atacante.cantidadCartasCementerio());
        assertEquals(1,defensor.cantidadCartasCementerio());
        assertEquals(atacante.getPuntosDeVida(), 8000);
        assertEquals(defensor.getPuntosDeVida(), 7500);
    }

    @Test
    public void testInvocarMonstruoEnPosicionDeAtaqueYAtacarMounstroEnPocisionAtaqueConPuntosAtacanteMenorDestruyeAtacanteYRestaDiferenciaEnPuntosJugadorQueAtaca()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test1",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",1000,100, Posicion.ATAQUE,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnAtaque(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(1,atacante.cantidadCartasCementerio());
        assertEquals(0,defensor.cantidadCartasCementerio());
        assertEquals(atacante.getPuntosDeVida(), 7500);
        assertEquals(defensor.getPuntosDeVida(), 8000);
    }

    @Test
    public void testInvocarMonstruosEnPosicionDeAtaqueYAtacarMounstroEnPocisionDefensaConMenoresPuntosDestruyeMounstroDefensor()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test1",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",1000,100, Posicion.DEFENSA,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnDefensa(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(1,defensor.cantidadCartasCementerio());
    }

    @Test
    public void testInvocarMonstruosEnPosicionDeAtaqueYAtacarMounstroEnPocisionDefensaConIgualesPuntosNoProduceCambios()
    {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test1",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",1000,500, Posicion.DEFENSA,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnDefensa(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(0,defensor.cantidadCartasCementerio());
        assertEquals(0,atacante.cantidadCartasCementerio());
    }

    @Test
    public void testInvocarMonstruosEnPosicionDeAtaqueYAtacarMounstroEnPocisionDefensaConMayoresPuntosNoDestruyeMounstroAtacanteYRestaDiferenciaEnPuntosAlJugadorQueAtaca()
    {
        Jugador atacante = new Jugador(); //Juego.getJuego().getJugadorActivo();
        Jugador defensor = new Jugador(); //Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new Monstruo("test1",500,2000, Posicion.ATAQUE,1);
        Monstruo monstruoDefensor = new Monstruo("test2",1000,1000, Posicion.DEFENSA,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        defensor.colocarEnDefensa(monstruoDefensor);

        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.UNO);

        assertEquals(0, atacante.cantidadCartasCementerio());
        assertEquals(0, defensor.cantidadCartasCementerio());
        assertEquals(7500, atacante.getPuntosDeVida());
        assertEquals(8000, defensor.getPuntosDeVida());

    }

    @Test
    public void testMonstruoConMenosDeCuatroEstrellasNoRequiereSacrificio(){
        Monstruo monstruo = new Monstruo("test",0,0,Posicion.ATAQUE,3);
        assertFalse(monstruo.requiereSacrificio());
    }

    @Test
    public void testMonstruoConMasDeCuatroEstrellasRequiereSacrificio(){
        Monstruo monstruo = new Monstruo("test2",0,0,Posicion.ATAQUE,6);
        assertTrue(monstruo.requiereSacrificio());
    }

    @Test
    public void testMonstruoConMasDeCuatroEstrellasRequiereSacrificarUnMonstruo(){
        Monstruo monstruo = new Monstruo("test",0,0,Posicion.ATAQUE,5);
        assertEquals(monstruo.cantidadASacrificar(),1);
    }

    @Test
    public void testMonstruoConMasDeSeisEstrellasRequiereSacrificarDosMonstruos(){
        Monstruo monstruo = new Monstruo("test",0,0,Posicion.ATAQUE,7);
        assertEquals(monstruo.cantidadASacrificar(),2);
    }


    @Test
    public void testColocarMonstruoCon5EstrellasSacrificaUnMonstruo(){
        Monstruo aSacrificar= new Monstruo("test1",0,0,Posicion.ATAQUE,3);
        Monstruo sacrificador = new Monstruo("test2",0,0,Posicion.ATAQUE,5);
        Jugador jugador = new Jugador();

        jugador.colocarEnAtaque(aSacrificar);
        jugador.colocarEnAtaque(sacrificador);
        assertEquals(1,jugador.cantidadCartasCementerio());
    }


    @Test
    public void testColocarMonstruoCon7EstrellasSacrificaDosMonstruos(){
        Jugador jugador = new Jugador();
        Monstruo aSacrificar1= new Monstruo("test1",0,0,Posicion.ATAQUE,3);
        Monstruo aSacrificar2= new Monstruo("test2",0,0,Posicion.ATAQUE,3);
        Monstruo sacrificador = new Monstruo("test3",0,0,Posicion.ATAQUE,7);
        jugador.colocarEnAtaque(aSacrificar1);
        jugador.colocarEnAtaque(aSacrificar2);
        jugador.colocarEnAtaque(sacrificador);
        assertEquals(2,jugador.cantidadCartasCementerio());
    }


    @Test
    public void testInvocarMonstruoEnPosicionDeAtaqueYAtacaAlOponenteSinMonstruosRestaLosPuntosDeAtaqueALosPuntosDeVidaDelJugadorQueDefiende() {
        Jugador atacante = new Jugador();
        Jugador defensor = new Jugador();
        Monstruo monstruoAtacante = new Monstruo("test",500,2000, Posicion.ATAQUE,1);
        atacante.colocarEnAtaque(monstruoAtacante);
        atacante.declararAtaqueDePosicionAPosicion(defensor,Casillero.UNO,Casillero.PUNTOSVIDA);
        
        assertEquals(0, atacante.cantidadCartasCementerio());
        assertEquals(7500, defensor.getPuntosDeVida());
        assertEquals(8000, atacante.getPuntosDeVida());

    }

}