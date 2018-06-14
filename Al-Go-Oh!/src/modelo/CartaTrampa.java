package modelo;

public class CartaTrampa extends Carta {
    Efecto efecto;

    public CartaTrampa(){
        super();
        this.estado = Colocacion.BOCAABAJO;
    }
}
