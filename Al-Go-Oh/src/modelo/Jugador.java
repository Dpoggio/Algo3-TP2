package modelo;

public class Jugador {
    private Tablero tablero;
    private int puntosDeVida;
    private Mano mano;
    private Mazo mazo;

    public Jugador(){
        tablero = new Tablero();
        puntosDeVida = 8000;
        this.mazo  = new Mazo();
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
        if(monstruo.requiereSacrificio())
        {
            this.sacrificarMonstruos(monstruo.cantidadASacrificar());
        }
        monstruo.setPosicion(Posicion.ATAQUE);
        campo.tirarCarta(monstruo);
    }

    private void sacrificarMonstruos(int cantidad) {
        this.tablero.sacrificarMonstruos(cantidad);
    }

    public void colocarEnDefensa(Monstruo monstruo){
        Tablero  campo = this.obtenerCampo();
        monstruo.setPosicion(Posicion.DEFENSA);
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
           
            monstruoAtacante.atacarMonstruo(this, defensor, monstruoDefensor);
        }
        else{
            monstruoAtacante.atacarPuntosDeVida(defensor);
        }

    }

    public Mano getMano() {
        return mano;
    }
}
