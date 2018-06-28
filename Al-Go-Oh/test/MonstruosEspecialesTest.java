import modelo.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import modelo.CartasMonstruosEspeciales.InsectoComeHombres;
import modelo.CartasMonstruosEspeciales.JinzoNro7;
import modelo.DragonDeOjosAzulesDefinitivo;
import modelo.Estados.EstadoAtaque;
import modelo.Excepciones.*;
import modelo.Monstruos.DragonBlancoDeOjosAzules;
import modelo.Monstruos.MonstruoGenerico;
import org.junit.Test;


public class MonstruosEspecialesTest {

    @Test
    public void testColocarJinzoEnPosicionDeAtaqueAtacaALosPuntosDeVidaDelOponenteDirectamenteSinImportarElMonstruoDelLadoOponente(){

        Jugador atacante = new Jugador(); //Juego.getJuego().getJugadorActivo();
        Jugador defensor = new Jugador(); //Juego.getJuego().getJugadorOponente();
        Monstruo jinzoNro7 = new JinzoNro7(new EstadoAtaque());
        Monstruo monstruoDefensor = new MonstruoGenerico("test",1000,1000, 4);
        atacante.invocar(jinzoNro7);
        defensor.colocar(monstruoDefensor);

        jinzoNro7.atacarMonstruo(monstruoDefensor);

        assertEquals(0, atacante.cantidadCartasCementerio());
        assertEquals(0, defensor.cantidadCartasCementerio());
        assertEquals(8000, atacante.getPuntosDeVida());
        assertEquals(7500, defensor.getPuntosDeVida());

    }


    @Test
    public void testAlAtacarAlInsectoComeHombresBocaAbajoEnPosicionDeDefensaSeDestruyeElMonstruoAtacante(){

        Jugador atacante = new Jugador(); //Juego.getJuego().getJugadorActivo();
        Jugador defensor = new Jugador(); //Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new MonstruoGenerico("test",1000,1000, 4);
        Monstruo insectoComeHombres = new InsectoComeHombres();
        atacante.invocar(monstruoAtacante);
        defensor.colocar(insectoComeHombres);

        monstruoAtacante.atacarMonstruo(insectoComeHombres);

        // Supuesto: El Insecto Come Hombres en el juego original cuando lo atacan el efecto se activa luego del calculo de puntos, por lo cual, este se destruye tambien.
        assertEquals(1, atacante.cantidadCartasCementerio());
        assertEquals(1, defensor.cantidadCartasCementerio());
        assertEquals(8000, atacante.getPuntosDeVida());
        assertEquals(8000, defensor.getPuntosDeVida());

    }

    @Test
    public void testAlAtacarAlInsectoComeHombresBocaAbajoEnPosicionDeDefensaConAtaqueMenorQueLaDefensaSeCalculaElDanioALosPuntosDeVidaYSeDestruyeElMonstruoAtacante(){

        Jugador atacante = new Jugador(); //Juego.getJuego().getJugadorActivo();
        Jugador defensor = new Jugador(); //Juego.getJuego().getJugadorOponente();
        Monstruo monstruoAtacante = new MonstruoGenerico("test",100,1000, 4);
        Monstruo insectoComeHombres = new InsectoComeHombres();
        atacante.invocar(monstruoAtacante);
        defensor.colocar(insectoComeHombres);

        monstruoAtacante.atacarMonstruo(insectoComeHombres);

        assertEquals(1, atacante.cantidadCartasCementerio());
        assertEquals(0, defensor.cantidadCartasCementerio());
        assertEquals(7500, atacante.getPuntosDeVida());
        assertEquals(8000, defensor.getPuntosDeVida());

    }


    @Test
    public void testInvocarAlDragonDeOjosAzulesDefinitivoDevuleveErrorSiSeInvocaDeFormaNormal(){
        Jugador jugador = new Jugador();
        Monstruo aSacrificar1= new MonstruoGenerico("test1",0,0,3);
        Monstruo aSacrificar2= new MonstruoGenerico("test2",0,0,3);
        Monstruo aSacrificar3= new MonstruoGenerico("test2",0,0,3);
        Monstruo dragonDeOjosAzulesDefinitivo = new DragonDeOjosAzulesDefinitivo();
        jugador.colocar(aSacrificar1);
        jugador.resetearInvocacionesPosibles();
        jugador.colocar(aSacrificar2);
        jugador.resetearInvocacionesPosibles();
        jugador.colocar(aSacrificar3);
        jugador.resetearInvocacionesPosibles();

        assertThrows(MonstruoDeFusionException.class , () -> { jugador.invocar(dragonDeOjosAzulesDefinitivo); });
        jugador.resetearInvocacionesPosibles();
        assertThrows(MonstruoDeFusionException.class , () -> { jugador.invocar(dragonDeOjosAzulesDefinitivo,aSacrificar1); });
        jugador.resetearInvocacionesPosibles();
        assertThrows(MonstruoDeFusionException.class , () -> { jugador.invocar(dragonDeOjosAzulesDefinitivo,aSacrificar1,aSacrificar2); });
        jugador.resetearInvocacionesPosibles();
        //assertThrows(MonstruoDeFusionException.class , () -> { jugador.invocar(dragonDeOjosAzulesDefinitivo,aSacrificar1,aSacrificar2,aSacrificar3); });
        jugador.resetearInvocacionesPosibles();
        assertThrows(MonstruoDeFusionException.class , () -> { jugador.colocar(dragonDeOjosAzulesDefinitivo); });
        jugador.resetearInvocacionesPosibles();
        assertThrows(MonstruoDeFusionException.class , () -> { jugador.colocar(dragonDeOjosAzulesDefinitivo,aSacrificar1); });
        jugador.resetearInvocacionesPosibles();
        assertThrows(MonstruoDeFusionException.class , () -> { jugador.colocar(dragonDeOjosAzulesDefinitivo,aSacrificar1,aSacrificar2); });
        jugador.resetearInvocacionesPosibles();
        assertThrows(MonstruoDeFusionException.class , () -> { jugador.colocar(dragonDeOjosAzulesDefinitivo,aSacrificar1,aSacrificar2,aSacrificar3); });
    }

    @Test
    public void testInvocarAlDragonDeOjosAzulesDefinitivoDestruye3DragonesBlancosDeOjosAzules(){
        Jugador jugador = new Jugador();
        Monstruo aSacrificar1= new MonstruoGenerico("test1",0,0,3);
        Monstruo aSacrificar2= new MonstruoGenerico("test2",0,0,3);
        Monstruo dragon1= new DragonBlancoDeOjosAzules();

        Monstruo aSacrificar3= new MonstruoGenerico("test3",0,0,3);
        Monstruo aSacrificar4= new MonstruoGenerico("test4",0,0,3);
        Monstruo dragon2= new DragonBlancoDeOjosAzules();

        Monstruo aSacrificar5= new MonstruoGenerico("test5",0,0,3);
        Monstruo aSacrificar6= new MonstruoGenerico("test6",0,0,3);
        Monstruo dragon3= new DragonBlancoDeOjosAzules();

        Monstruo dragonDeOjosAzulesDefinitivo = new DragonDeOjosAzulesDefinitivo();

        jugador.colocar(aSacrificar1);
        jugador.resetearInvocacionesPosibles();
        jugador.colocar(aSacrificar2);
        jugador.resetearInvocacionesPosibles();
        jugador.colocar(dragon1,aSacrificar1,aSacrificar2);
        jugador.resetearInvocacionesPosibles();

        jugador.colocar(aSacrificar3);
        jugador.resetearInvocacionesPosibles();
        jugador.colocar(aSacrificar4);
        jugador.resetearInvocacionesPosibles();
        jugador.colocar(dragon2,aSacrificar3,aSacrificar4);
        jugador.resetearInvocacionesPosibles();

        jugador.colocar(aSacrificar5);
        jugador.resetearInvocacionesPosibles();
        jugador.colocar(aSacrificar6);
        jugador.resetearInvocacionesPosibles();
        jugador.colocar(dragon3,aSacrificar5,aSacrificar6);
        jugador.resetearInvocacionesPosibles();

        jugador.invocar(dragonDeOjosAzulesDefinitivo, dragon1, dragon2, dragon3);

        assertEquals(9,jugador.cantidadCartasCementerio());
    }


}
