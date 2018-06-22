package modelo;

public class MonstruoTMP extends Carta {
    private Estado estado;

    // atacante.atacarMonstruo(defensor)
    public void atacarMonstruo(MonstruoTMP defensor){

        int puntosAtaque = 200;

        defensor.recibirDanio( this);


    }

    // Metodo que recibe el defensor
    private void recibirDanio(Monstruo atacante) {
        estado.recibirDanio(atacante, this)
    }

}
