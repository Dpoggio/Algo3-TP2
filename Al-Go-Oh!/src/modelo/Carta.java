package modelo;

public abstract class Carta{
    protected Colocacion estado;
    protected String tipo;

    public Carta(){
        estado = Colocacion.BOCAARRIBA;
    }

    public boolean estaBocaAbajo(){
        return estado == Colocacion.BOCAABAJO;
    }
}