package modelo;

public class EstadoAtaque extends Estado {



    public void recibirDanio(Monstruo atacante, Monstruo defensor) {
        if (atacante.getPuntosAtaque() < defensor.getPuntosDefensa()){
            atacante.destruir();
        }

    }
}
