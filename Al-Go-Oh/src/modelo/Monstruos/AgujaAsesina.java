package modelo.Monstruos;

import modelo.Estado;
import modelo.Monstruo2;

public class AgujaAsesina extends Monstruo2 {

    public AgujaAsesina(Estado _estado){
        puntosAtaque = 1200;
        puntosDefensa = 1000;
        estado = _estado;
    }

}
