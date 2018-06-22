package modelo;

public class EstadoDefensaBocaArriba extends Estado {

    @Override
    public void recibirDanio(Monstruo2 atacante, Monstruo2 defensor) {
        atacante.comparararConDefensa(defensor);
    }

}
