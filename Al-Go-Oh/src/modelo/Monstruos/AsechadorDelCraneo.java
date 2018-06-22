package modelo.Monstruos;

import modelo.Estado;
import modelo.Monstruo2;

public class AsechadorDelCraneo extends Monstruo2 {

    public AsechadorDelCraneo(Estado _estado){
        puntosAtaque = 900;
        puntosDefensa = 800;
        estado = _estado;
    }

}
