package modelo;

public class Monstruo extends Carta {

    protected int estrellas;

    protected int puntosAtaque;

    protected int puntosDefensa;
    protected int adicionalesDeAtaque ;
    protected int adicionalesDeDefensa;

    protected int nivel;

    protected Posicion posicion;

    public Posicion getPosicion(){
        return this.posicion;
    }

    public Monstruo(){
        super();
    }

    public Monstruo(int puntosAtaque, int puntosDefensa){
        super();
        this.puntosAtaque = puntosAtaque;
        this.puntosDefensa = puntosDefensa;
        this.posicion = Posicion.DEFENSA;
    }

    public Monstruo(String nombre, int puntosAtaque, int puntosDefensa, Posicion posicion, int estrellas){
        super();
        this.nombre = nombre;
        this.puntosAtaque = puntosAtaque;
        this.puntosDefensa = puntosDefensa;
        this.adicionalesDeAtaque = 0;
        this.adicionalesDeDefensa = 0;
        this.posicion = posicion;
        this.estrellas = estrellas;
    }

    @Override
    public void destruir(){
        tableroCarta.matarMonstruo(this);
    }


    public void atacarMonstruo(Jugador atacante,Jugador defensor, Monstruo objetivo)
    {
        if(objetivo.getPosicion() == Posicion.ATAQUE) {
            if( objetivo.noDefiendeEnAtaque(this) ){
                //defensor.obtenerCampo().matarMonstruo(objetivo);
                objetivo.destruir();
                defensor.restarPuntosDeVida(this.getPuntosAtaque() - objetivo.getPuntosAtaque());
            }
            if( this.noDefiendeEnAtaque(objetivo) ){
                //atacante.obtenerCampo().matarMonstruo(this);
                this.destruir();
                atacante.restarPuntosDeVida(objetivo.getPuntosAtaque() - this.getPuntosAtaque());
            }
        }
        else{
            if( objetivo.noDefiendeEnDefensa(this) ){
                //defensor.obtenerCampo().matarMonstruo(objetivo);
                objetivo.destruir();
            }
            if( objetivo.daniaEnDefensa(this) ){
                atacante.restarPuntosDeVida(objetivo.getPuntosDefensa() - this.getPuntosAtaque());
            }

            if (objetivo.getEstado() == Colocacion.BOCAABAJO) {
                objetivo.setEstado(Colocacion.BOCAARRIBA);
                objetivo.activarEfecto(this);
            }

        }

    }


    public boolean noDefiendeEnAtaque(Monstruo MonstruoAtacante) {
        int puntosAtaqueDefensor = this.getPuntosAtaque();
        int puntosAtaqueAtacante = MonstruoAtacante.getPuntosAtaque();
        return (puntosAtaqueAtacante >= puntosAtaqueDefensor);
    }


    public boolean noDefiendeEnDefensa(Monstruo monstruoAtacante){
        int puntosDefensaDefensor = this.getPuntosDefensa();
        int puntosAtaqueAtacante = monstruoAtacante.getPuntosAtaque();
        return (puntosAtaqueAtacante > puntosDefensaDefensor);
    }

    public boolean daniaEnDefensa(Monstruo monstruoAtacante){
        int puntosDefensaDefensor = this.getPuntosDefensa();
        int puntosAtaqueAtacante = monstruoAtacante.getPuntosAtaque();
        return (puntosAtaqueAtacante < puntosDefensaDefensor);
    }



    public void atacarPuntosDeVida(Jugador oponente){
        oponente.restarPuntosDeVida(this.puntosAtaque);
    }

    public int getPuntosAtaque() {
        return this.puntosAtaque + this.adicionalesDeAtaque;
    }
    protected void setPuntosAtaque(int _puntosAtaque){
        this.puntosAtaque = _puntosAtaque;
    }
    protected int getPuntosDefensa(){
        return this.puntosDefensa + this.adicionalesDeDefensa;
    }
    protected void setPuntosDefensa(int _puntosDefensa){
        this.puntosDefensa = _puntosDefensa;
    }

    protected int getEstrellas(){
        return this.estrellas;
    }

    public boolean requiereSacrificio(){
        return this.getEstrellas() > 4;
    }

    public int cantidadASacrificar(){
        if(this.getEstrellas() > 6){
            return 2;
        }
        else if(this.getEstrellas() > 4){
            return 1;
        }

        return 0;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }



    void sumarAdicionalAlataque(int adicional) {
        this.adicionalesDeAtaque += adicional;
        
    }

    void sumarAdicionalAlaDefensa(int adicional) {
        this.adicionalesDeDefensa += adicional;   
    }
     
    void ponerAdicionalesEnCero(){
        this.adicionalesDeAtaque = 0;
        this.adicionalesDeDefensa = 0;
    }   
}
