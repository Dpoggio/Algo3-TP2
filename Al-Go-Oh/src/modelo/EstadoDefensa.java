package modelo;

public class EstadoDefensa extends Estado {


    public int recibirDanio(int puntosAtaque, Monstruo atacante) {

        return (puntosAtaque - puntos);

    }
}
