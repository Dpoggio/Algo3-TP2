package modelo;

import java.awt.*;
import java.io.IOException;

public class Jugador {
    private Tablero tablero;
    private int puntosDeVida;
    private Mano mano;
    private Mazo mazo;

    /*** TMP ****/
    protected int cantidad_destruida = 0;

    public void sumar_cantidad_destruida() {
        cantidad_destruida += 1;
    }


    public int getCantidadDestruida(){
        return cantidad_destruida;
    }
    /************/




    public Jugador(){
        tablero = new Tablero();
        puntosDeVida = 8000;
        try {
            this.mazo  = new Mazo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mano = new Mano();
    }

    public Tablero obtenerCampo(){
        return this.tablero;
    }

    public int getPuntosDeVida() {
        return puntosDeVida;
    }

    public void setPuntosDeVida(int puntosDeVida) {
        this.puntosDeVida = puntosDeVida;
    }

    public void restarPuntosDeVida(int puntosDeVidaMenos){
        puntosDeVida -= puntosDeVidaMenos;
    }

    public int cantidadCartasCementerio() {

        return tablero.cantidadCartasCementerio();
    }

    public void colocarEnAtaque(Monstruo monstruo){
        Tablero  campo = this.obtenerCampo();
        monstruo.asociarTablero(campo);
        if(monstruo.requiereSacrificio())
        {
            this.sacrificarMonstruos(monstruo.cantidadASacrificar());
        }
        monstruo.setPosicion(Posicion.ATAQUE);
        monstruo.setEstado(Colocacion.BOCAARRIBA);
        campo.tirarCarta(monstruo);
    }

    private void sacrificarMonstruos(int cantidad) {
        this.tablero.sacrificarMonstruos(cantidad);
    }

    public void colocarEnDefensa(Monstruo monstruo){
        Tablero  campo = this.obtenerCampo();
        monstruo.setPosicion(Posicion.DEFENSA);
        monstruo.setEstado(Colocacion.BOCAABAJO);
        campo.tirarCarta(monstruo);
    }

    public void colocarCarta(CartaMagica carta){
        this.obtenerCampo().tirarCarta(carta);
    }


    public void colocarCarta(CartaTrampa carta) {
        this.obtenerCampo().tirarCarta(carta);
    }
    
    public void colocarCarta(CartaCampo carta) {
        this.obtenerCampo().tirarCarta(carta);
    }
        
    
    public void declararAtaqueDePosicionAPosicion(Jugador defensor, Casillero casilleroAtacante, Casillero casilleroDefensor) {
        
        Monstruo monstruoAtacante = tablero.obtenerMonstruoEnCasillero(casilleroAtacante);
        
        if (casilleroDefensor != Casillero.PUNTOSVIDA) {
            Monstruo monstruoDefensor = defensor.obtenerCampo().obtenerMonstruoEnCasillero(casilleroDefensor);
           
            Ataque atacarMonstruo = new Ataque(this,defensor, monstruoAtacante, monstruoDefensor);
            atacarMonstruo.realizarAtaque();

            //monstruoAtacante.atacarMonstruo(this, defensor, monstruoDefensor);
        }
        else{
            Ataque atacarPuntosVida = new Ataque(this,defensor, monstruoAtacante);
            atacarPuntosVida.atacarPuntosDeVida();
            //monstruoAtacante.atacarPuntosDeVida(this,defensor);
        }

    }

    public Mano getMano() {
        return mano;
    }

    public void extraerCartaDelMazo() {
        Carta carta = mazo.extaerCarta();
        mano.agregarCarta(carta);
    }

    public void activarCartaCampo() {
        this.obtenerCampo().activarCartaCampo();
    }
       


    public int obtenerTamanioMazo() {
        return this.getMazo().getTamanio();
    }

    private Mazo getMazo() {
        return this.mazo;
    }
}
