package modelo.Monstruos;

import modelo.Estado;
import modelo.Monstruo2;

public class AgresorOscuro extends Monstruo2 {

    public AgresorOscuro(Estado _estado) {
        puntosAtaque = 1200;
        puntosDefensa = 1200;
        estado = _estado;
    }

}

