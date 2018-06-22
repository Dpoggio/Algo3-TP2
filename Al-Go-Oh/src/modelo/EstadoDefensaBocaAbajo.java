package modelo;

public class EstadoDefensaBocaAbajo extends Estado {

    public void recibirDanio(Monstruo2 atacante, Monstruo2 defensor) {
        atacante.comparararConDefensa(defensor);
    }
}
