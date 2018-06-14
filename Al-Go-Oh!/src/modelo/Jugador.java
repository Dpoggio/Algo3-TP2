package modelo;

public class Jugador {
    private Tablero tablero;
    private int puntosDeVida;

    public Jugador(){
        tablero = new Tablero();
        puntosDeVida = 8000;
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
        for (int i = 0; i < cantidad; i++){
            this.tablero.getZonaMonstruo().sacrificarMonstruo();
        }
    }

    public void colocarEnDefensa(Monstruo monstruo){
        Tablero  campo = this.obtenerCampo();
        monstruo.setPosicion(Posicion.DEFENSA);
        campo.tirarCarta(monstruo);
    }

    public boolean declararAtaque(Monstruo monstruo)
    {
        return this.tablero.getZonaMonstruo().declararAtaque(monstruo,null);
    }

    public void colocarCarta(CartaMagica carta){
        this.obtenerCampo().tirarCarta(carta);
    }


    public void colocarCarta(CartaTrampa carta) {
        this.obtenerCampo().tirarCarta(carta);
    }
}
