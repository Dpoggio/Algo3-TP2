package modelo;

public class EstadoAtaque extends Estado {

    public void recibirDanio(Monstruo2 atacante, Monstruo2 defensor) {
        atacante.comparararConAtaque(defensor);
    }
}
