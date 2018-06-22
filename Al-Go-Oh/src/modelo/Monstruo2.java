package modelo;

public class Monstruo2 extends Carta {
    protected int puntosAtaque;
    protected int puntosDefensa;
    protected Estado estado;
    protected Jugador jugador;

    public int getAtaque(){
        return puntosAtaque;
    }

    public int getDefensa(){
        return puntosDefensa;
    }

    @Override
    public void asociarJugador(Jugador _jugador){
        jugador = _jugador;
    }

    // atacante.atacarMonstruo(defensor)
    public void atacarMonstruo(Monstruo2 defensor){
        defensor.recibirDanio( this);
    }

    // Metodo que recibe el defensor
    private void recibirDanio(Monstruo2 atacante) {
        estado.recibirDanio(atacante, this);
    }


    public void comparararConDefensa(Monstruo2 defensor) {
        if(puntosAtaque > defensor.getDefensa()){
            defensor.destruir();
        }
        else{
            this.daniarJugador(defensor.getDefensa() - puntosAtaque);
        }
    }

    public void comparararConAtaque(Monstruo2 defensor) {
        if(puntosAtaque >= defensor.getAtaque()){
            defensor.destruir();
            defensor.daniarJugador(puntosAtaque - defensor.getAtaque());
        }

        if(puntosAtaque <= defensor.getAtaque()){
            this.destruir();
            this.daniarJugador(defensor.getAtaque() - puntosAtaque);
        }
    }

    @Override
    public void destruir() {
        jugador.sumar_cantidad_destruida();
    }

    public void daniarJugador(int puntos){
        jugador.restarPuntosDeVida(puntos);
    }

}
